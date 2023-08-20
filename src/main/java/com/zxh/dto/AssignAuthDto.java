package com.zxh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/14 18:56
 *
 * 接收给角色分配权限请求的json数据的dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignAuthDto {

    // 角色id

    private Integer roleId;

    // 分配给角色的权限菜单id
    private List<Integer> authIds;
}
