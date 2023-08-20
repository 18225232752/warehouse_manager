package com.zxh.mapper;

import com.zxh.entity.Supply;

import java.util.List;

/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/15 10:09
*/

public interface SupplyMapper {

    // 查询所有供应商
    List<Supply> findAllSupply();
}