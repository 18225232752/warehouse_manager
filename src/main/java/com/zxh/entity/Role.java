package com.zxh.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/14 9:22
 */

/**
 * 角色表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    private Integer roleId;

    private String roleName;

    private String roleDesc;

    private String roleCode;

    private String roleState;

    private Integer createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private String getCode; // 角色创建人

}