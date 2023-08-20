package com.zxh.service;

import com.zxh.entity.OutStore;
import com.zxh.entity.Result;
import com.zxh.page.Page;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/16 16:15
 */

public interface OutStoreService {

    // 添加出库单的业务方法
    Result saveOutStore(OutStore outStore);

    // 分页查询出库单的业务方法
    Page queryOutStorePage(Page page, OutStore outStore);

    // 确认出库的业务方法
    Result outStoreConfirm(OutStore outStore);
}
