package com.zxh.service.impl;

import com.zxh.entity.InStore;
import com.zxh.entity.Result;
import com.zxh.mapper.ProductMapper;
import com.zxh.mapper.PurchaseMapper;
import com.zxh.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.zxh.mapper.InStoreMapper;
import com.zxh.service.InStoreService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
  * Created with IntelliJ IDEA.
  * @author taehyang
  * @date 2023/8/17 11:27
*/

@Service
public class InStoreServiceImpl implements InStoreService{

    @Autowired
    private InStoreMapper inStoreMapper;

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Autowired
    private ProductMapper productMapper;

    // 添加入库单的业务方法
    @Transactional
    @Override
    public Result saveInStore(InStore inStore, Integer buyId) {
        // 添加入库单
        int i = inStoreMapper.insertInStore(inStore);
        if (i > 0) {
            int j = purchaseMapper.setIsInById(buyId);
            if (j > 0) {
                return Result.ok("入库单添加成功!");
            }
            return Result.err(Result.CODE_ERR_BUSINESS, "入库单添加失败!");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "入库单添加失败!");
    }

    // 分页查询入库单的业务方法
    @Override
    public Page queryInStorePage(Page page, InStore inStore) {
        // 查询入库单行数
        Integer count = inStoreMapper.findInStoreCount(inStore);

        // 分页查询入库单
        List<InStore> inStoreList = inStoreMapper.findInStorePage(page, inStore);

        // 封装分页信息
        page.setTotalNum(count);
        page.setResultList(inStoreList);

        return page;
    }

    // 确认入库的业务方法
    @Transactional
    @Override
    public Result inStoreConfirm(InStore inStore) {
        // 修改入库单状态
        int i = inStoreMapper.setIsInById(inStore.getInsId());
        if (i > 0) {
            // 修改商品库存
            int j = productMapper.setInventById(inStore.getProductId(), inStore.getInNum());
            if (j > 0) {
                return Result.ok("入库单确认成功！");
            }
            return Result.err(Result.CODE_ERR_BUSINESS, "入库单确认失败！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "入库单确认失败！");
    }
}
