package com.zxh.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/15 10:08
*/

/**
    * 品牌
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand implements Serializable {
    private Integer brandId;

    private String brandName;

    private String brandLeter;

    private String brandDesc;

    private static final long serialVersionUID = 1L;
}