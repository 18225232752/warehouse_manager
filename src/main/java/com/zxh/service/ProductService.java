package com.zxh.service;

import com.zxh.entity.Product;
import com.zxh.entity.Result;
import com.zxh.page.Page;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/15 10:56
 */

public interface ProductService {

    // 分页查询商品的业务方法
    Page queryProductPage(Page page, Product product);

    // 添加商品的业务方法
    Result saveProduct(Product product);

    // 修改商品上/下架状态的业务方法
    Result updateStateByPid(Product product);

    // 删除商品
    Result deleteProductByIds(List<Integer> productIdList);

    // 修改商品得业务方法
    Result setProductById(Product product);
}
