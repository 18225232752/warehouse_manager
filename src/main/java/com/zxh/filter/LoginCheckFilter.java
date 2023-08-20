package com.zxh.filter;

import com.alibaba.fastjson.JSON;
import com.zxh.entity.Result;
import com.zxh.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/12 19:02
 */
public class LoginCheckFilter implements Filter {

    private StringRedisTemplate redisTemplate;

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // 白名单
        List<String> urlList = new ArrayList<>();
        urlList.add("/captcha/captchaImage");
        urlList.add("/login");
        urlList.add("/product/img-upload");

        // 白名单直接放行
        String url = req.getServletPath();
        if (urlList.contains(url) || url.contains("/img/upload")) {
            chain.doFilter(req, resp);
            return;
        }

        // 其他请求则进行校验是否携带token，以及判断是否过期
        // 放行
        String token = req.getHeader(WarehouseConstants.HEADER_TOKEN_NAME);
        if (StringUtils.hasText(token) && Boolean.TRUE.equals(redisTemplate.hasKey(token))) {
            chain.doFilter(req, resp);
            return;
        }
        // 拦截
        Result result = Result.err(Result.CODE_ERR_UNLOGINED, "尚未登录");
        String jsonStr = JSON.toJSONString(result);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write(jsonStr);
        out.flush();
        out.close();
    }
}
