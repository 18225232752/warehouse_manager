package com.zxh.mapper;

import com.zxh.entity.ProductType;

import java.util.List;

/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/15 10:09
*/

public interface ProductTypeMapper {

    // 查询所有商品分类
    List<ProductType> findAllProductType();

    // 根据名称或编码查询商品分类
    ProductType findTypeByCodeOrName(ProductType productType);

    // 添加商品分类
    int insertProductType(ProductType productType);

    // 根据分类id或父类id删除分类
    int removeProductType(Integer typeID);

    // 修改商品分类
    int setProductTypeById(ProductType productType);
}