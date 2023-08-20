package com.zxh.controller;

import com.zxh.entity.Result;
import com.zxh.service.StoreService;
import com.zxh.vo.StoreCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/17 18:21
 */
@RestController
@RequestMapping("/statistics")
public class statisticsController {

    @Autowired
    private StoreService storeService;

    // 统计仓库库存
    @RequestMapping("/store-invent")
    public Result storeInvent() {
        List<StoreCountVo> storeCountVoList = storeService.queryStoreCount();
        return Result.ok(storeCountVoList);
    }
}
