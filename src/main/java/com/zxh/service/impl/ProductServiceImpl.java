package com.zxh.service.impl;

import com.zxh.entity.Product;
import com.zxh.entity.Result;
import com.zxh.page.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.zxh.mapper.ProductMapper;
import com.zxh.service.ProductService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/15 10:56
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    // 分页查询商品的业务方法
    @Override
    public Page queryProductPage(Page page, Product product) {
        // 查询商品行数
        Integer count = productMapper.findProductRowCount(product);
        // 分页查询商品
        List<Product> productList = productMapper.findProductPage(page, product);
        // 组装分页信息
        page.setTotalNum(count);
        page.setResultList(productList);
        return page;
    }

    // 添加商品的业务方法

    // 图片资源路径前缀
    @Value("${file.access-path}")
    private String fileAccessPath;

    @Override
    public Result saveProduct(Product product) {

        // 查询型号是否已存在
        Product prct = productMapper.findProductByNum(product.getProductNum());
        if (prct != null) { // 商品型号已存在
            return Result.err(Result.CODE_ERR_BUSINESS, "该型号商品已存在！");
        }

        // 拼接路径前缀
        product.setImgs(fileAccessPath + product.getImgs());

        // 添加商品
        int i = productMapper.insertProduct(product);
        if (i > 0) {
            return Result.ok("商品添加成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "商品添加失败！");
    }

    @Override
    public Result updateStateByPid(Product product) {
        int i = productMapper.setStateByPid(product.getProductId(), product.getUpDownState());
        if (i > 0) {
            return Result.ok("商品上/下架状态修改成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "商品上/下架状态修改失败！");
    }

    // 删除商品
    @Override
    public Result deleteProductByIds(List<Integer> productIdList) {
        int i = productMapper.removeProductByIds(productIdList);
        if (i > 0) {
            return Result.ok("商品删除成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "商品删除失败！");
    }

    // 修改商品的业务方法
    @Override
    public Result setProductById(Product product) {
        // 判断修改后的型号是否已存在
        Product prod = productMapper.findProductByNum(product.getProductNum());
        if (prod != null && !prod.getProductId().equals(product.getProductId())) {
            return Result.err(Result.CODE_ERR_BUSINESS, "改型号已存在");
        }

        // 判断商品图片是否被修改
        if (!product.getImgs().contains(fileAccessPath)) {
            product.setImgs(fileAccessPath + product.getImgs());
        }

        // 修改商品
        int i = productMapper.setProductById(product);
        if (i > 0) {
            return Result.ok("商品修改成功！");
        }

        return Result.err(Result.CODE_ERR_BUSINESS, "商品修改失败！");
    }
}
