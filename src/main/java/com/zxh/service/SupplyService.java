package com.zxh.service;

import com.zxh.entity.Supply;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/15 10:09
 */

public interface SupplyService {

    // 查询所有供应商的业务方法
    List<Supply> queryAllSupply();
}
