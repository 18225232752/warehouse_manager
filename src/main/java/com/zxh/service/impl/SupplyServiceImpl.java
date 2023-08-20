package com.zxh.service.impl;

import com.zxh.entity.Supply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.zxh.mapper.SupplyMapper;
import com.zxh.service.SupplyService;

import java.util.List;

/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/15 10:09
*/

@Service
@CacheConfig(cacheNames = "com.zxh.service.impl.SupplyServiceImpl")
public class SupplyServiceImpl implements SupplyService{

    @Autowired
    private SupplyMapper supplyMapper;

    // 查询所有供应商

    @Cacheable(key = "'all:supply'")
    @Override
    public List<Supply> queryAllSupply() {
        return supplyMapper.findAllSupply();
    }
}
