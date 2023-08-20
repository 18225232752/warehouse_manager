package com.zxh.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/13 11:04
*/

/**
    * 权限表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auth implements Serializable {
    private Integer authId;

    private Integer parentId;

    private String authName;

    private String authDesc;

    private Integer authGrade;

    private String authType;

    private String authUrl;

    private String authCode;

    private Integer authOrder;

    private String authState;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    // 存放当前菜单的子菜单
    private List<Auth> childAuth;

}