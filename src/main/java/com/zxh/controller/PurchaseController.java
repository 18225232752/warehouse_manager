package com.zxh.controller;

import com.zxh.entity.*;
import com.zxh.page.Page;
import com.zxh.service.InStoreService;
import com.zxh.service.PurchaseService;
import com.zxh.service.StoreService;
import com.zxh.utils.TokenUtils;
import com.zxh.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/16 15:37
 */
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    // 注入PurchaseService
    @Autowired
    private PurchaseService purchaseService;

    // 注入StoreService
    @Autowired
    private StoreService storeService;

    // 注入InStoreService
    @Autowired
    private InStoreService inStoreService;

    @Autowired
    private TokenUtils tokenUtils;

    // 添加采购单
    @RequestMapping("/purchase-add")
    public Result addPurchase(@RequestBody Purchase purchase) {
        return purchaseService.savePurchase(purchase);
    }

    // 查询所有仓库
    @RequestMapping("/store-list")
    public Result storeList() {
        // 执行业务
        List<Store> storeList = storeService.queryAllStore();
        // 响应
        return Result.ok(storeList);
    }

    // 分页查询采购单
    @RequestMapping("/purchase-page-list")
    public Result purchaseListPage(Page page, Purchase purchase) {
        // 执行业务
        page = purchaseService.queryPurchasePage(page, purchase);
        // 响应
        return Result.ok(page);
    }

    // 删除采购单
    @RequestMapping("/purchase-delete/{buyId}")
    public Result deletePurchase(@PathVariable Integer buyId) {
        return purchaseService.deletePurchaseById(buyId);
    }

    // 修改采购单
    @RequestMapping("/purchase-update")
    public Result updatePurchase(@RequestBody Purchase purchase) {
        return purchaseService.updatePurchaseById(purchase);
    }

    // 生成入库单
    @RequestMapping("/in-warehouse-record-add")
    public Result addInStore(@RequestBody Purchase purchase, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {

        // 获取当前用户id
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int createBy = currentUser.getUserId();

        // 创建InStore对象封装入库信息
        InStore inStore = new InStore();
        inStore.setCreateBy(createBy);
        inStore.setStoreId(purchase.getStoreId());
        inStore.setProductId(purchase.getProductId());
        inStore.setInNum(purchase.getFactBuyNum());

        return inStoreService.saveInStore(inStore, purchase.getBuyId());
    }

}
