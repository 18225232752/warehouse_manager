package com.zxh.service.impl;

import com.zxh.dto.AssignRoleDto;
import com.zxh.entity.Result;
import com.zxh.entity.User;
import com.zxh.entity.UserRole;
import com.zxh.mapper.RoleMapper;
import com.zxh.mapper.UserMapper;
import com.zxh.mapper.UserRoleMapper;
import com.zxh.page.Page;
import com.zxh.service.UserService;
import com.zxh.utils.DigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/12 18:09
 */
@Service
public class UserServiceImpl implements UserService {

    // 注入UserMapper
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public User queryUserByCode(String userCode) {
        return userMapper.findUserByCode(userCode);
    }

    @Override
    public Page queryUserByPage(Page page, User user) {
        // 查询用户行数
        Integer count = userMapper.findUserRowCount(user);
        // 分页查询用户
        List<User> userList = userMapper.findUserByPage(page, user);
        // 组装所有分页信息
        page.setTotalNum(count);
        page.setResultList(userList);

        return page;
    }

    @Override
    public Result saveUser(User user) {
        // 判断账号是否已存在
        User u = userMapper.findUserByCode(user.getUserCode());
        if (u != null) {
            return Result.err(Result.CODE_ERR_BUSINESS, "账号已存在！");
        }

        // 密码加密
        String password = DigestUtil.hmacSign(user.getUserPwd());
        user.setUserPwd(password);

        // 执行添加
        int i = userMapper.insertUser(user);
        if (i > 0) {
            return Result.ok("用户添加成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "用户添加失败");
    }

    @Override
    public Result setUserState(User user) {
        int i = userMapper.setStateByUid(user.getUserId(), user.getUserState());
        if (i > 0) {
            return Result.ok("用户状态修改成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "用户状态修改失败！");
    }

    @Transactional
    @Override
    public void assignRole(AssignRoleDto assignRoleDto) {

        userRoleMapper.removeUserRoleByUid(assignRoleDto.getUserId());

        List<String> roleNameList = assignRoleDto.getRoleCheckList();
        for(String roleName: roleNameList) {
            Integer roleId = roleMapper.findRoleIdByName(roleName);
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(assignRoleDto.getUserId());
            userRoleMapper.insertUserRole(userRole);
        }
    }

    @Override
    public Result removeUserByIds(List<Integer> userIdList) {
        int i = userMapper.setIsDeleteByUids(userIdList);
        if (i > 0) {
            return Result.ok("用户删除成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "用户删除失败！");
    }

    @Override
    public Result setUserById(User user) {
        int i = userMapper.setUserNameByUid(user);
        if (i > 0) {
            return Result.ok("修改成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "修改失败！");
    }

    @Override
    public Result setPwdById(Integer userId) {
        // 获取加密后的密码
        String password = DigestUtil.hmacSign("123456");

        // 重置密码
        int i = userMapper.setPwdByUid(userId, password);
        if (i > 0) {
            return Result.ok("密码重置成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "密码重置失败！");
    }
}
