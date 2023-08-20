package com.zxh.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.zxh.mapper.RoleAuthMapper;
import com.zxh.service.RoleAuthService;
/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/14 17:47
*/

@Service
public class RoleAuthServiceImpl implements RoleAuthService{

    @Resource
    private RoleAuthMapper roleAuthMapper;

}
