package com.zxh.mapper;

import com.zxh.entity.Unit;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/15 10:10
 */

public interface UnitMapper {

    // 查询所有单位
    List<Unit> findAllUnit();
}