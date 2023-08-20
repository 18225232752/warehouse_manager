package com.zxh.controller;

import com.zxh.entity.CurrentUser;
import com.zxh.entity.OutStore;
import com.zxh.entity.Result;
import com.zxh.entity.Store;
import com.zxh.page.Page;
import com.zxh.service.OutStoreService;
import com.zxh.service.StoreService;
import com.zxh.utils.TokenUtils;
import com.zxh.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/16 16:17
 */
@RestController
@RequestMapping("/outstore")
public class OutStoreController {

    // 注入OutStoreService
    @Autowired
    private OutStoreService outStoreService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private StoreService storeService;

    // 添加出库单
    @RequestMapping("/outstore-add")
    public Result addOutStore(@RequestBody OutStore outStore, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        // 获取当前用户id
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int createBy = currentUser.getUserId();

        outStore.setCreateBy(createBy);

        return outStoreService.saveOutStore(outStore);
    }

    // 查询所有仓库
    @RequestMapping("/store-list")
    public Result storeList() {
        List<Store> storeList = storeService.queryAllStore();
        return Result.ok(storeList);
    }

    // 分页查询出库单
    @RequestMapping("/outstore-page-list")
    public Result outStoreListPage(Page page, OutStore outStore) {
        // 执行业务
        page = outStoreService.queryOutStorePage(page, outStore);
        // 响应
        return Result.ok(page);
    }

    // 确认出库
    @RequestMapping("/outstore-confirm")
    public Result confirmOutStore(@RequestBody OutStore outStore) {
        return outStoreService.outStoreConfirm(outStore);
    }
}
