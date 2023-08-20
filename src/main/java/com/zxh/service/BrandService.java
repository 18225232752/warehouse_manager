package com.zxh.service;

import com.zxh.entity.Brand;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/15 10:08
 */

public interface BrandService {

    // 查询所有品牌的业务方法
    List<Brand> queryAllBrand();
}
