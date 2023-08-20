package com.zxh.service;

import com.zxh.entity.ProductType;
import com.zxh.entity.Result;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/15 10:09
 */

public interface ProductTypeService {

    // 查询商品分类树的业务方法
    List<ProductType> productTypeTree();

    // 校验分类编码是否存在的业务方法
    Result checkTypeCode(String typeCode);

    // 添加商品分类的业务方法
    Result saveProductType(ProductType productType);

    // 根据分类id或父类id删除分类的业务方法
    Result deleteProductType(Integer typeId);

    // 修改商品分类的业务方法
    Result setProductType(ProductType productType);
}
