package com.zxh.service;

import com.zxh.entity.Store;
import com.zxh.vo.StoreCountVo;

import java.util.List;

/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/15 10:08
*/

public interface StoreService{

    // 查询所有仓库的业务方法
    List<Store> queryAllStore();

    // 查询每个仓库商品数量的业务方法
    List<StoreCountVo> queryStoreCount();
}
