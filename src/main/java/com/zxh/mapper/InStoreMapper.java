package com.zxh.mapper;

import com.zxh.entity.InStore;
import com.zxh.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/17 11:27
 */

public interface InStoreMapper {

    // 添加入库单
    int insertInStore(InStore inStore);

    // 查询入库单行数
    Integer findInStoreCount(InStore inStore);

    // 分页查询入库单
    List<InStore> findInStorePage(@Param("page") Page page, @Param("inStore") InStore inStore);

    // 修改入库单状态为已入库
    int setIsInById(Integer inStoreId);
}