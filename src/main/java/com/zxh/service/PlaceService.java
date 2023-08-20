package com.zxh.service;

import com.zxh.entity.Place;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/15 10:10
 */

public interface PlaceService {

    // 查询所有产地的业务方法
    List<Place> queryAllPrice();
}
