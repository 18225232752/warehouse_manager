package com.zxh.mapper;

import com.zxh.entity.Product;
import com.zxh.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/15 10:56
 */

public interface ProductMapper {

    // 查询商品行数
    Integer findProductRowCount(Product product);

    // 分页查询商品
    List<Product> findProductPage(@Param("page") Page page, @Param("product") Product product);

    // 根据型号查询商品
    Product findProductByNum(String productNum);

    // 添加商品
    int insertProduct(Product product);

    // 修改商品上/下架状态
    int setStateByPid(@Param("productId") Integer productId, @Param("upDownState") String upDownState);

    // 删除商品
    int removeProductByIds(List<Integer> productList);

    // 修改商品
    int setProductById(Product product);

    // 修改商品库存
    int setInventById(@Param("productId") Integer productId, @Param("invent") Integer invent);

    // 查询商品库存
    int findInventById(Integer productId);
}