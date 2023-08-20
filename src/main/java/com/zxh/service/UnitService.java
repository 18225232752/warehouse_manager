package com.zxh.service;

import com.zxh.entity.Unit;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/15 10:10
 */

public interface UnitService {
    // 查询所有单位的业务方法
    List<Unit> queryAllUnit();
}
