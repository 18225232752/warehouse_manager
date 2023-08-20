package com.zxh.mapper;

import com.zxh.entity.OutStore;
import com.zxh.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/16 16:15
 */

public interface OutStoreMapper {
    // 添加出库单
    int insertOutStore(OutStore outStore);

    // 查询出库单行数
    Integer findOutStoreCount(OutStore outStore);

    // 分页查询出库单
    List<OutStore> findOutStorePage(@Param("page") Page page, @Param("outStore") OutStore outStore);

    // 修改出库单状态为已出库
    int setIsOutById(Integer outStoreId);
}