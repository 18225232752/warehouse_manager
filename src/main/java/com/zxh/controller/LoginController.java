package com.zxh.controller;

import com.google.code.kaptcha.Producer;
import com.zxh.entity.*;
import com.zxh.service.AuthService;
import com.zxh.service.UserService;
import com.zxh.utils.DigestUtil;
import com.zxh.utils.TokenUtils;
import com.zxh.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/12 15:55
 */
@RestController
public class LoginController {

    // 注入DefaultKaptcha的bean对象
    @Autowired
    private Producer producer;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserService userService;

    // 注入TokenUtils的ben对象
    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthService authService;


    // 接收前端请求生成验证码，并将验证码返回
    @RequestMapping("/captcha/captchaImage")
    public void captchaImage(HttpServletResponse resp) {

        ServletOutputStream out = null;

        try {
            // 生成验证码文本
            String text = producer.createText();
            // 生成验证码图片(内存中)
            BufferedImage image = producer.createImage(text);
            // 验证码写入redis，设置3分钟的有效时长
            redisTemplate.opsForValue().set(text, text, 60 * 3, TimeUnit.SECONDS);
            // 设置响应正文image/jpeg
            resp.setContentType("image/jpeg");
            // 验证码图片返回给前端
            out = resp.getOutputStream();
            // 写入响应的输出流
            ImageIO.write(image, "jpg", out);
            // 刷新
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 接收登录请求，返回token
    @RequestMapping("/login")
    public Result login(@RequestBody LoginUser loginUser) {

        // 拿到录入的验证码
        String code = loginUser.getVerificationCode();
        if (Boolean.FALSE.equals(redisTemplate.hasKey(code))) {
            return Result.err(Result.CODE_ERR_BUSINESS, "验证码错误");
        }

        // 根据账号查询用户
        User user = userService.queryUserByCode(loginUser.getUserCode());
        if (user != null) { // 账号存在
            if (user.getUserState().equals(WarehouseConstants.USER_STATE_PASS)) { // 用户已审核
                // 拿到用户录入的密码
                String userPwd = loginUser.getUserPwd();
                // md5加密
                userPwd = DigestUtil.hmacSign(userPwd);
                if (userPwd.equals(user.getUserPwd())) { // 密码一致
                    // 生成jwt token 并存入redis
                    CurrentUser currentUser = new CurrentUser(user.getUserId(), user.getUserCode(), user.getUserName());
                    String token = tokenUtils.loginSign(currentUser, userPwd);
                    return Result.ok("登录成功！", token);
                } else { // 密码错误
                    return Result.err(Result.CODE_ERR_BUSINESS, "密码错误！");
                }

            } else { // 用户未审核
                return Result.err(Result.CODE_ERR_BUSINESS, "用户未审核！");
            }
        } else { // 账号不存在
            return Result.err(Result.CODE_ERR_BUSINESS, "账号不存在！");
        }
    }

    // 获取当前登录的用户信息
    @RequestMapping("/curr-user")
    public Result currentUser(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        // 从token中解析当前登录用户的信息
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        // 响应
        return Result.ok(currentUser);
    }

    // 加载用户权限菜单树
    @RequestMapping("/user/auth-list")
    public Result loadAuthTree(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        // 从token中解析当前登录用户的信息
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int userId = currentUser.getUserId();
        // 执行业务
        List<Auth> authTreeList= authService.authTreeByUid(userId);
        // 响应
        return Result.ok(authTreeList);
    }

    // 退出登录
    @RequestMapping("/logout")
    public Result logout(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        // 从redis删除token
        redisTemplate.delete(token);
        // 响应
        return Result.ok("退出系统！");
    }


}
