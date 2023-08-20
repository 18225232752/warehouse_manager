package com.zxh.entity;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/15 10:09
*/

/**
    * 商品分类表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductType implements Serializable {
    private Integer typeId;

    private Integer parentId;

    private String typeCode;

    private String typeName;

    private String typeDesc;

    // ----------追加属性
    private List<ProductType> childProductCategory;
}