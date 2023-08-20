package com.zxh.service;

import com.zxh.dto.AssignAuthDto;
import com.zxh.entity.Auth;
import com.zxh.entity.Result;
import com.zxh.entity.Role;
import com.zxh.page.Page;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/14 9:22
 */

public interface RoleService {

    // 查询所有角色的业务方法
    List<Role> queryAllRole();

    // 查询分配给用户的角色的业务方法
    List<Role> queryUserRoleByUid(Integer userId);

    // 分页查询角色的业务方法
    Page queryRolePage(Page page, Role role);

    // 添加角色的业务方法
    Result saveRole(Role role);

    // 更改角色状态(启用/禁用)
    Result setRoleState(Role role);

    // 删除角色的业务方法
    Result deleteRoleById(Integer roleId);

    // 查询用户权限菜单的业务方法
    List<Integer> queryRoleAuthIds(Integer roleId);

    // 为角色分配权限的业务方法
    void saveRoleAuth(AssignAuthDto assignAuthDto);

    // 修改角色的业务方法
    Result setRoleByRid(Role role);

}
