package com.zxh.service.impl;

import com.zxh.entity.UserRole;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.zxh.mapper.UserRoleMapper;
import com.zxh.service.UserRoleService;

import java.util.List;

/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/14 9:47
*/

@Service
public class UserRoleServiceImpl implements UserRoleService{

    @Resource
    private UserRoleMapper userRoleMapper;

}
