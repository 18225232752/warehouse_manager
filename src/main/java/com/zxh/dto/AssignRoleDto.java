package com.zxh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/14 11:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignRoleDto {
    private Integer userId;

    private List<String> roleCheckList;
}
