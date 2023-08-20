package com.zxh.mapper;

import com.zxh.entity.Purchase;
import com.zxh.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/16 15:36
 */

public interface PurchaseMapper {

    // 添加采购单
    int insertPurchase(Purchase purchase);

    // 查询采购单行数
    Integer findPurchaseCount(Purchase purchase);

    // 分页查询采购单
    List<Purchase> findPurchasePage(@Param("page") Page page, @Param("purchase") Purchase purchase);

    // 删除采购单
    int removePurchaseById(Integer buyId);

    // 修改采购单
    int setNumById(Purchase purchase);

    // 修改采购单状态为已入库
    int setIsInById(Integer buyId);

}