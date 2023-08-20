package com.zxh.config;

import com.zxh.filter.LoginCheckFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.Filter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/12 19:08
 */
@Configuration
public class ServletConfig {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        // 创建FilterRegistrationBean对象
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        // 创建自定义的过滤器
        LoginCheckFilter loginCheckFilter = new LoginCheckFilter();
        // 手动注入redisTemplate
        loginCheckFilter.setRedisTemplate(redisTemplate);
        // 将过滤器注册到FilterRegistrationBean的bean对象
        filterRegistrationBean.setFilter(loginCheckFilter);
        // 指定过滤器拦截的请求
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
