package com.zxh.service.impl;

import com.zxh.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.zxh.mapper.PlaceMapper;
import com.zxh.service.PlaceService;

import java.util.List;

/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/15 10:10
*/

@Service
@CacheConfig(cacheNames = "com.zxh.service.impl.PlaceServiceImpl")
public class PlaceServiceImpl implements PlaceService{

    @Autowired
    private PlaceMapper placeMapper;

    // 查询所有产地
    @Cacheable(key = "'all:place'")
    @Override
    public List<Place> queryAllPrice() {
        return placeMapper.findAllPlace();
    }
}
