package com.zxh.mapper;

import com.zxh.entity.Role;
import com.zxh.page.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/14 9:22
 */
@Mapper
public interface RoleMapper {

    // 查询所有角色
    List<Role> findAllRole();

    // 查询分配给用户的角色
    List<Role> findUserRoleByUid(Integer userId);

    // 根据角色名称查询角色id
    Integer findRoleIdByName(String roleName);

    // 查询角色行数
    Integer findRoleRowCount(Role role);

    // 分页查询角色的方法
    List<Role> findRolePage(@Param("page") Page page, @Param("role") Role role);

    // 根据角色名称或角色代码查询角色
    Role findRoleByNameOrCode(@Param("roleName") String roleName, @Param("roleCode") String roleCode);

    // 添加角色
    int insertRole(Role role);

    // 更改角色状态(启用/禁用)
    int setRoleStateByRid(@Param("roleId") Integer roleId, @Param("roleState") String roleState);

    // 删除角色
    int removeRoleById(Integer roleId);

    // 修改角色
    int setDescByRid(Role role);
}