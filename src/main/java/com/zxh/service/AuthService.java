package com.zxh.service;

import com.zxh.entity.Auth;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * @author taehyang
 * @date 2023/8/13 11:04
 */

public interface AuthService{

    // 查询用户菜单树
    List<Auth> authTreeByUid(Integer userId);

    // 查询所有的权限菜单的业务方法
    List<Auth> allAuthTree();

}
