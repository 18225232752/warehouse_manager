package com.zxh.mapper;

import com.zxh.entity.Auth;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/13 11:04
*/
@Mapper
public interface AuthMapper {

    // 根据userId查询用户权限下的所有菜单
    List<Auth> findAuthByUid(Integer userId);

    // 查询所有的权限菜单
    List<Auth> findAllAuth();
}