package com.zxh.service;

import com.zxh.entity.Purchase;
import com.zxh.entity.Result;
import com.zxh.page.Page;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/16 15:36
 */

public interface PurchaseService {

    // 添加采购单的业务方法
    Result savePurchase(Purchase purchase);

    // 分页查询采购单的业务方法
    Page queryPurchasePage(Page page, Purchase purchase);


    // 删除采购单的业务方法
    Result deletePurchaseById(Integer buyId);

    // 修改采购单的业务方法
    Result updatePurchaseById(Purchase purchase);
}
