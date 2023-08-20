package com.zxh.service.impl;

import com.zxh.dto.AssignAuthDto;
import com.zxh.entity.Auth;
import com.zxh.entity.Result;
import com.zxh.entity.Role;
import com.zxh.entity.RoleAuth;
import com.zxh.mapper.AuthMapper;
import com.zxh.mapper.RoleAuthMapper;
import com.zxh.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.zxh.mapper.RoleMapper;
import com.zxh.service.RoleService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/14 9:22
 */

@Service
@CacheConfig(cacheNames = "com.zxh.service.impl.RoleServiceImpl") // 指定缓存名称
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleAuthMapper roleAuthMapper;

    @Autowired
    private AuthMapper authMapper;

    // 查询所有角色
    @Cacheable(key = "'all:role'") // 指定缓存的key
    @Override
    public List<Role> queryAllRole() {
        return roleMapper.findAllRole();
    }

    // 查询分配给用户的角色
    @Override
    public List<Role> queryUserRoleByUid(Integer userId) {
        return roleMapper.findUserRoleByUid(userId);
    }

    // 分页查询角色
    @Override
    public Page queryRolePage(Page page, Role role) {
        // 查询角色行数
        Integer count = roleMapper.findRoleRowCount(role);

        // 分页查询角色
        List<Role> roleList = roleMapper.findRolePage(page, role);

        // 组装分页信息
        page.setTotalNum(count);
        page.setResultList(roleList);

        return page;
    }

    @CacheEvict(key = "'all:role'")
    @Override
    public Result saveRole(Role role) {
        // 判断角色名称或角色代码是否已存在
        Role r = roleMapper.findRoleByNameOrCode(role.getRoleName(), role.getRoleCode());
        if (r != null) {
            return Result.err(Result.CODE_ERR_BUSINESS, "角色已存在！");
        }
        int i = roleMapper.insertRole(role);
        if (i > 0) {
            return Result.ok("角色添加成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "角色添加失败！");
    }

    @CacheEvict(key = "'all:role'")
    @Override
    public Result setRoleState(Role role) {
        int i = roleMapper.setRoleStateByRid(role.getRoleId(), role.getRoleState());
        if (i > 0) {
            return Result.ok("角色启用/禁用成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "角色启用/禁用失败！");
    }

    @CacheEvict(key = "'all:role'")
    @Override
    public Result deleteRoleById(Integer roleId) {

        int i = roleMapper.removeRoleById(roleId);
        if (i > 0) {
            // 删除角色权限关系
            roleAuthMapper.removeRoleAuthByRid(roleId);
            return Result.ok("角色删除成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "角色删除失败！");
    }

    @Override
    public List<Integer> queryRoleAuthIds(Integer roleId) {
        return roleAuthMapper.findAuthIdsByRid(roleId);
    }

    @Transactional
    @Override
    public void saveRoleAuth(AssignAuthDto assignAuthDto) {
        // 删除原先的权限
        roleAuthMapper.removeRoleAuthByRid(assignAuthDto.getRoleId());

        // 重新分配权限
        List<Integer> authIdList = assignAuthDto.getAuthIds();
        for (Integer authId : authIdList) {
            RoleAuth roleAuth = new RoleAuth();
            roleAuth.setRoleId(assignAuthDto.getRoleId());
            roleAuth.setAuthId(authId);
            roleAuthMapper.insertRoleAuth(roleAuth);
        }
    }

    @CacheEvict(key = "'all:role'")
    @Override
    public Result setRoleByRid(Role role) {
        int i = roleMapper.setDescByRid(role);
        if (i > 0) {
            return Result.ok("角色修改成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "角色修改失败！");
    }
}
