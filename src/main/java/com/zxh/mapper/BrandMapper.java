package com.zxh.mapper;

import com.zxh.entity.Brand;

import java.util.List;

/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/15 10:08
*/

public interface BrandMapper {

    // 查询所有品牌
    List<Brand> findAllBrand();
}