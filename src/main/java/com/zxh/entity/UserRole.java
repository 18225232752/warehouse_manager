package com.zxh.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/14 9:47
*/

/**
    * 用户角色表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole implements Serializable {
    private Integer userRoleId;

    private Integer roleId;

    private Integer userId;

    private static final long serialVersionUID = 1L;
}