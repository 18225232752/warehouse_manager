package com.zxh.mapper;

import com.zxh.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/14 9:47
*/
@Mapper
public interface UserRoleMapper {
    // 删除用户已分配的角色
    int removeUserRoleByUid(Integer userId);

    // 添加用户角色
    int insertUserRole(UserRole userRole);
}