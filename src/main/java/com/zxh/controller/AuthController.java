package com.zxh.controller;

import com.zxh.entity.Auth;
import com.zxh.entity.Result;
import com.zxh.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/14 18:14
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    // 查询所有权限菜单树
    @RequestMapping("/auth-tree")
    public Result loadAllAuthTree() {
        // 执行业务
        List<Auth> allAuthTree = authService.allAuthTree();
        // 响应
        return Result.ok(allAuthTree);
    }
}
