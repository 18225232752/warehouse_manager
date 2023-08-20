package com.zxh.controller;

import com.zxh.entity.ProductType;
import com.zxh.entity.Result;
import com.zxh.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/16 16:34
 */
@RestController
@RequestMapping("/productCategory")
public class ProductTypeController {

    // 注入ProductTypeService
    @Autowired
    private ProductTypeService productTypeService;

    // 查询商品分类树
    @RequestMapping("/product-category-tree")
    public Result productCategoryTree() {
        // 执行业务
        List<ProductType> typeTreeList = productTypeService.productTypeTree();
        // 响应
        return Result.ok(typeTreeList);
    }

    // 校验分类编码是否已存在
    @RequestMapping("/verify-type-code")
    public Result checkTypeCode(String typeCode) {
        return productTypeService.checkTypeCode(typeCode);
    }

    // 添加商品分类
    @RequestMapping("/type-add")
    public Result addProductType(@RequestBody ProductType productType) {
        return productTypeService.saveProductType(productType);
    }

    // 删除商品分类
    @RequestMapping("/type-delete/{typeId}")
    public Result typeDelete(@PathVariable Integer typeId) {
        return productTypeService.deleteProductType(typeId);
    }

    // 修改商品分类
    @RequestMapping("/type-update")
    public Result updateProductType(@RequestBody ProductType productType) {
        return productTypeService.setProductType(productType);
    }
}
