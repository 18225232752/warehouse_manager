package com.zxh.service.impl;

import com.zxh.entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.zxh.mapper.BrandMapper;
import com.zxh.service.BrandService;

import java.util.List;

/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/15 10:08
*/

@Service
@CacheConfig(cacheNames = "com.zxh.service.impl.BrandServiceImpl")
public class BrandServiceImpl implements BrandService{

    @Autowired
    private BrandMapper brandMapper;

    @Cacheable(key = "'all:brand'")
    @Override
    public List<Brand> queryAllBrand() {
        return brandMapper.findAllBrand();
    }
}
