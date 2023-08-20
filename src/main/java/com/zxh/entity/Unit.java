package com.zxh.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/15 10:10
*/

/**
    * 规格单位表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Unit implements Serializable {
    private Integer unitId;

    private String unitName;

    private String unitDesc;

    private static final long serialVersionUID = 1L;
}