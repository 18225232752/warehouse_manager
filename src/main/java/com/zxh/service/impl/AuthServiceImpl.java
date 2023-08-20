package com.zxh.service.impl;

import com.alibaba.fastjson.JSON;
import com.zxh.entity.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.zxh.mapper.AuthMapper;
import com.zxh.service.AuthService;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/13 11:04
*/

@Service
@CacheConfig(cacheNames = "com.zxh.service.impl.AuthServiceImpl") // redis缓存key的前缀
public class AuthServiceImpl implements AuthService{

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    // 查询用户菜单树
    @Override
    public List<Auth> authTreeByUid(Integer userId) {

        // 先从redis中查询缓存的用户菜单树
        String authTreeJson = redisTemplate.opsForValue().get("authTree:" + userId);
        if (StringUtils.hasText(authTreeJson)) { // redis中存在缓存
            // 将菜单树List<Auth>转的json串转回List<Auth>返回
            List<Auth> authTreeList = JSON.parseArray(authTreeJson, Auth.class);
            return authTreeList;
        }

        // redis中不存在缓存
        List<Auth> allAuthList = authMapper.findAuthByUid(userId);
        // 将所有菜单List<Auth>转为菜单树List<Auth>
        List<Auth> authTreeList = allAuthToAuthTree(allAuthList, 0);
        // 向redis写入菜单树
        redisTemplate.opsForValue().set("authTree:" + userId, JSON.toJSONString(authTreeList));
        return authTreeList;
    }

    @Cacheable(key = "'all:authTree'")
    @Override
    public List<Auth> allAuthTree() {
        // 查询所有权限菜单
        List<Auth> allAuthList = authMapper.findAllAuth();
        // 将所有权限菜单转为权限菜单树
        List<Auth> authTreeList = allAuthToAuthTree(allAuthList, 0);
        return authTreeList;
    }

    // 将所有菜单List<Auth>转成菜单树List<Auth>的递归算法
    private List<Auth> allAuthToAuthTree(List<Auth> allAuthList, Integer pid) {

        // 查询出所有一级菜单
        List<Auth> firstLevelAuthList = new ArrayList<>();
        for(Auth auth: allAuthList) {
            if (auth.getParentId().equals(pid)) {
                firstLevelAuthList.add(auth);
            }
        }

        // 拿到每个一级菜单的所有二级菜单
        for(Auth firstAuth: firstLevelAuthList) {
            List<Auth> secondLevelAuthList = allAuthToAuthTree(allAuthList, firstAuth.getAuthId());
            firstAuth.setChildAuth(secondLevelAuthList);
        }
        return firstLevelAuthList;
    }
}
