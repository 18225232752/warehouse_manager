package com.zxh.service.impl;

import com.zxh.entity.Store;
import com.zxh.vo.StoreCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.zxh.mapper.StoreMapper;
import com.zxh.service.StoreService;

import java.util.List;

/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/15 10:08
*/

@Service
@CacheConfig(cacheNames = "com.zxh.service.impl.StoreServiceImpl")
public class StoreServiceImpl implements StoreService{

    @Autowired
    private StoreMapper storeMapper;

    @Cacheable(key = "'all:store'")
    @Override
    public List<Store> queryAllStore() {
        return storeMapper.findAllStore();
    }

    // 查询每个仓库商品数量的业务方法
    @Override
    public List<StoreCountVo> queryStoreCount() {
        return storeMapper.findStoreCount();
    }
}
