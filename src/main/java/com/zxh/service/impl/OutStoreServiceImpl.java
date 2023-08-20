package com.zxh.service.impl;

import com.zxh.entity.OutStore;
import com.zxh.entity.Result;
import com.zxh.mapper.ProductMapper;
import com.zxh.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.zxh.mapper.OutStoreMapper;
import com.zxh.service.OutStoreService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/16 16:15
 */

@Service
public class OutStoreServiceImpl implements OutStoreService {

    @Autowired
    private OutStoreMapper outStoreMapper;

    @Autowired
    private ProductMapper productMapper;

    // 添加出库单的业务方法
    @Override
    public Result saveOutStore(OutStore outStore) {
        int i = outStoreMapper.insertOutStore(outStore);
        if (i > 0) {
            return Result.ok("出库单添加成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "出库单添加失败！");
    }

    // 分页查询出库单的业务方法
    @Override
    public Page queryOutStorePage(Page page, OutStore outStore) {
        // 查询出库单行数
        Integer count = outStoreMapper.findOutStoreCount(outStore);

        // 分页查询出库单
        List<OutStore> outStoreList = outStoreMapper.findOutStorePage(page, outStore);

        // 组装分页信息
        page.setTotalNum(count);
        page.setResultList(outStoreList);

        return page;
    }

    // 确认出库的业务方法
    @Transactional
    @Override
    public Result outStoreConfirm(OutStore outStore) {
        // 判断库存是否充足
        int invent = productMapper.findInventById(outStore.getProductId());
        if (invent < outStore.getOutNum()) {
            return Result.err(Result.CODE_ERR_BUSINESS, "商品库存不足！");
        }

        // 修改出库单状态
        int i = outStoreMapper.setIsOutById(outStore.getOutsId());
        if (i > 0) {
            // 修改商品库存
            int j = productMapper.setInventById(outStore.getProductId(), -outStore.getOutNum());
            if (j > 0) {
                return Result.ok("确认处理成功！");
            }
            return Result.err(Result.CODE_ERR_BUSINESS, "确认出库失败！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "确认出库失败！");
    }
}
