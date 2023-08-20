package com.zxh.service;

import com.zxh.dto.AssignRoleDto;
import com.zxh.entity.Result;
import com.zxh.entity.Role;
import com.zxh.entity.User;
import com.zxh.page.Page;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/12 18:07
 * <p>
 * user_info表的service接口
 */
public interface UserService {

    // 根据账号查询用户的业务方法
    User queryUserByCode(String userCode);

    // 分页查询用户的业务方法
    Page queryUserByPage(Page page, User user);

    // 添加用户的业务方法
    Result saveUser(User user);

    // 更新用户状态的业务方法
    Result setUserState(User user);

    // 为用户分配角色的业务方法
    void assignRole(AssignRoleDto assignRoleDto);

    // 根据用户ids修改用户删除状态的业务方法
    Result removeUserByIds(List<Integer> userIdList);

    // 修改用户的业务方法
    Result setUserById(User user);

    // 重置密码的业务方法
    Result setPwdById(Integer userId);


}
