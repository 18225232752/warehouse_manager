package com.zxh.service;

import com.zxh.entity.InStore;
import com.zxh.entity.Result;
import com.zxh.page.Page;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/17 11:27
 */

public interface InStoreService {

    // 添加入库单的业务方法
    Result saveInStore(InStore inStore, Integer buyId);

    // 分页查询入库单的业务方法
    Page queryInStorePage(Page page, InStore inStore);

    // 确认入库的业务方法
    Result inStoreConfirm(InStore inStore);
}
