package com.zxh.service.impl;

import com.zxh.entity.Product;
import com.zxh.entity.ProductType;
import com.zxh.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.zxh.mapper.ProductTypeMapper;
import com.zxh.service.ProductTypeService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/15 10:09
 */

@Service
@CacheConfig(cacheNames = "com.zxh.service.impl.ProductTypeServiceImpl")
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeMapper productTypeMapper;


    // 查询商品分类树
    @Cacheable(key = "'all:typeTree'")
    @Override
    public List<ProductType> productTypeTree() {
        System.out.println("来到productTypeTree");
        // 查询所有商品分类
        List<ProductType> allProductTypeList = productTypeMapper.findAllProductType();
        // 转为商品分类树
        List<ProductType> typeTreeList = allTypeToTypeTree(allProductTypeList, 0);
        return typeTreeList;
    }

    // 校验分类名称或编码是否存在的业务方法
    @Override
    public Result checkTypeCode(String typeCode) {
        // 根据分类编码查询分类，判断是否存在
        ProductType productType = new ProductType();
        productType.setTypeCode(typeCode);
        ProductType prodType = productTypeMapper.findTypeByCodeOrName(productType);
        return Result.ok(prodType == null);
    }

    // 添加商品分类的业务方法
    @CacheEvict(key = "'all:typeTree'")
    @Override
    public Result saveProductType(ProductType productType) {
        ProductType prodType = new ProductType();
        prodType.setTypeName(productType.getTypeName());
        ProductType prodCategory = productTypeMapper.findTypeByCodeOrName(prodType);
        if (prodCategory != null) {
            return Result.err(Result.CODE_ERR_BUSINESS, "分类名称已存在！");
        }

        // 添加分类
        int i = productTypeMapper.insertProductType(productType);
        if (i > 0) {
            return Result.ok("商品分类添加成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "商品分类添加失败！");
    }

    // 根据分类id或父类id删除分类的业务方法
    @CacheEvict(key = "'all:typeTree'")
    @Override
    public Result deleteProductType(Integer typeId) {
        int i = productTypeMapper.removeProductType(typeId);
        if (i > 0) {
            return Result.ok("商品分类删除成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "商品分类删除失败！");
    }

    // 修改商品分类的业务方法
    @CacheEvict(key = "'all:typeTree'")
    @Override
    public Result setProductType(ProductType productType) {
        // 判断修改的分类名称是否已存在
        ProductType prodType = new ProductType();
        prodType.setTypeName(productType.getTypeName());
        ProductType prodCategory = productTypeMapper.findTypeByCodeOrName(prodType);
        if (prodCategory != null && !prodCategory.getTypeId().equals(productType.getTypeId())) {
            return Result.err(Result.CODE_ERR_BUSINESS, "商品分类已存在！");
        }

        // 修改
        int i = productTypeMapper.setProductTypeById(productType);
        if (i > 0) {
            return Result.ok("商品分类修改成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "商品分类修改失败！");
    }

    // 将所有商品分类转为商品分类树的递归算法
    private List<ProductType> allTypeToTypeTree(List<ProductType> typeList, Integer pid) {
        // 拿到所有一级分类
        List<ProductType> firstLevelType = new ArrayList<>();

        for (ProductType productType : typeList) {
            if (productType.getParentId().equals(pid)) {
                firstLevelType.add(productType);
            }
        }

        // 查出每个以及分类下的所有二级分类
        for (ProductType productType : firstLevelType) {
            List<ProductType> secondLevelType = allTypeToTypeTree(typeList, productType.getTypeId());
            productType.setChildProductCategory(secondLevelType);
        }

        return firstLevelType;
    }
}
