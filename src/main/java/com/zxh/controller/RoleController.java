package com.zxh.controller;

import com.zxh.dto.AssignAuthDto;
import com.zxh.entity.Auth;
import com.zxh.entity.CurrentUser;
import com.zxh.entity.Result;
import com.zxh.entity.Role;
import com.zxh.page.Page;
import com.zxh.service.RoleService;
import com.zxh.utils.TokenUtils;
import com.zxh.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/14 9:25
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    // 注入RoleService
    @Autowired
    private RoleService roleService;

    @Autowired
    private TokenUtils tokenUtils;

    // 查询所有角色
    @RequestMapping("/role-list")
    public Result roleList() {
        // 执行业务
        List<Role> roleList = roleService.queryAllRole();
        // 响应
        return Result.ok(roleList);
    }

    // 分页查询所有角色
    @RequestMapping("/role-page-list")
    public Result roleListPage(Page page, Role role) {
        // 执行业务
        page = roleService.queryRolePage(page, role);
        // 响应
        return Result.ok(page);
    }

    // 添加角色
    @RequestMapping("/role-add")
    public Result addRole(@RequestBody Role role, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        // 获取当前用户id
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int createBy = currentUser.getUserId();

        role.setCreateBy(createBy);

        return roleService.saveRole(role);
    }

    // 启用/禁用角色
    @RequestMapping("/role-state-update")
    public Result updateRoleState(@RequestBody Role role) {
        return roleService.setRoleState(role);
    }

    // 删除角色
    @RequestMapping("/role-delete/{roleId}")
    public Result deleteRole(@PathVariable Integer roleId) {
        return roleService.deleteRoleById(roleId);
    }

    // 查询用户权限菜单
    @RequestMapping("/role-auth")
    public Result roleAuth(Integer roleId) {
        // 执行业务
        List<Integer> authIdList = roleService.queryRoleAuthIds(roleId);
        // 响应
        return Result.ok(authIdList);
    }

    // 为角色分配权限菜单
    @RequestMapping("/auth-grant")
    public Result grantAuth(@RequestBody AssignAuthDto assignAuthDto) {
        // 执行业务
        roleService.saveRoleAuth(assignAuthDto);
        // 响应
        return Result.ok("权限分配成功！");
    }

    // 修改角色
    @RequestMapping("/role-update")
    public Result updateRole(@RequestBody Role role, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        // 获取当前用户id
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int updateBy = currentUser.getUserId();

        role.setUpdateBy(updateBy);

        return roleService.setRoleByRid(role);
    }

}
