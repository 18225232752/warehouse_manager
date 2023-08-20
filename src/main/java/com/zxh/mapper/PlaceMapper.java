package com.zxh.mapper;

import com.zxh.entity.Place;

import java.util.List;

/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/15 10:10
*/

public interface PlaceMapper {

    // 查询所有产地
    List<Place> findAllPlace();
}