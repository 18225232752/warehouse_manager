package com.zxh.controller;

import com.zxh.entity.InStore;
import com.zxh.entity.Result;
import com.zxh.entity.Store;
import com.zxh.page.Page;
import com.zxh.service.InStoreService;
import com.zxh.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/17 14:49
 */
@RestController
@RequestMapping("/instore")
public class InStoreController {

    // 注入InStoreService
    @Autowired
    private InStoreService inStoreService;

    // 注入StoreService
    @Autowired
    private StoreService storeService;

    // 查询所有仓库
    @RequestMapping("/store-list")
    public Result storeList() {
        List<Store> storeList = storeService.queryAllStore();
        return Result.ok(storeList);
    }

    // 分页查询入库单
    @RequestMapping("/instore-page-list")
    public Result inStoreListPage(Page page, InStore inStore) {
        // 执行业务
        page = inStoreService.queryInStorePage(page, inStore);
        // 响应
        return Result.ok(page);
    }

    // 确认入库
    @RequestMapping("/instore-confirm")
    public Result confirmInStore(@RequestBody InStore inStore) {
        return inStoreService.inStoreConfirm(inStore);
    }
}
