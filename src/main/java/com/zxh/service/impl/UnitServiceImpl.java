package com.zxh.service.impl;

import com.zxh.entity.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.zxh.mapper.UnitMapper;
import com.zxh.service.UnitService;

import java.util.List;

/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/15 10:10
*/

@Service
@CacheConfig(cacheNames = "com.zxh.service.impl.UnitServiceImpl")
public class UnitServiceImpl implements UnitService{

    @Autowired
    private UnitMapper unitMapper;

    // 查询所有单位的业务方法
    @Cacheable(key = "'all:unit'")
    @Override
    public List<Unit> queryAllUnit() {
        return unitMapper.findAllUnit();
    }
}
