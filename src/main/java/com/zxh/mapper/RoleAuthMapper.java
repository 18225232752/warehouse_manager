package com.zxh.mapper;

import com.zxh.entity.RoleAuth;

import java.util.List;

/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/14 17:47
*/

public interface RoleAuthMapper {
    // 根据角色id删除角色权限关系
    int removeRoleAuthByRid(Integer roleId);

    // 查询角色的权限菜单
    List<Integer> findAuthIdsByRid(Integer roleId);

    // 添加角色权限
    int insertRoleAuth(RoleAuth roleAuth);
}