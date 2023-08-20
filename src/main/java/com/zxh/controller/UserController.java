package com.zxh.controller;

import com.zxh.dto.AssignRoleDto;
import com.zxh.entity.*;
import com.zxh.page.Page;
import com.zxh.service.RoleService;
import com.zxh.service.UserRoleService;
import com.zxh.service.UserService;
import com.zxh.utils.TokenUtils;
import com.zxh.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/13 17:09
 */
@RestController
@RequestMapping("/user")
public class UserController {

    // 注入UserService
    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private RoleService roleService;

    // 分页查询用户
    @RequestMapping("/user-list")
    public Result userList(Page page, User user) {
        // 执行业务
        page = userService.queryUserByPage(page, user);
        // 响应
        return Result.ok(page);
    }

    // 添加用户
    @RequestMapping("/addUser")
    public Result addUser(@RequestBody User user, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        // 拿到当前登录用户id
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int createBy = currentUser.getUserId();
        user.setCreateBy(createBy);

        return userService.saveUser(user);
    }

    // 修改用户状态
    @RequestMapping("/updateState")
    public Result updateUserState(@RequestBody User user) {
        return userService.setUserState(user);
    }

    // 查询用户已分配角色
    @RequestMapping("/user-role-list/{userId}")
    public Result userRoleList(@PathVariable Integer userId) {
        // 执行业务
        List<Role> roleList = roleService.queryUserRoleByUid(userId);
        // 响应
        return Result.ok(roleList);
    }

    // 为用户分配角色
    @RequestMapping("/assignRole")
    public Result assignRole(@RequestBody AssignRoleDto assignRoleDto) {
        // 执行业务
        userService.assignRole(assignRoleDto);
        // 响应
        return Result.ok("分配角色成功！");
    }

    // 删除单个用户
    @RequestMapping("/deleteUser/{userId}")
    public Result deleteUserById(@PathVariable Integer userId) {
        return userService.removeUserByIds(Arrays.asList(userId));
    }

    // 批量删除用户
    @RequestMapping("/deleteUserList")
    public Result deleteUserByIds(@RequestBody List<Integer> userIdList) {
        return userService.removeUserByIds(userIdList);
    }

    // 修改用户
    @RequestMapping("/updateUser")
    public Result updateUser(@RequestBody User user, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int updateBy = currentUser.getUserId();

        user.setUpdateBy(updateBy);

        return userService.setUserById(user);
    }

    // 重置密码
    @RequestMapping("/updatePwd/{userId}")
    public Result resetPassword(@PathVariable Integer userId) {
        return userService.setPwdById(userId);
    }

}
