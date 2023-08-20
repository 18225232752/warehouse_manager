package com.zxh.mapper;

import com.zxh.entity.Store;
import com.zxh.vo.StoreCountVo;

import java.util.List;

/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/15 10:08
*/

public interface StoreMapper {

    // 查询所有仓库
    List<Store> findAllStore();

    // 查询每个仓库商品数量
    List<StoreCountVo> findStoreCount();
}