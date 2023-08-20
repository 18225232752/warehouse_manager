package com.zxh.controller;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zxh.entity.*;
import com.zxh.page.Page;
import com.zxh.service.*;
import com.zxh.service.impl.SupplyServiceImpl;
import com.zxh.utils.TokenUtils;
import com.zxh.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/15 10:21
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    // 注入StoreService
    @Autowired
    private StoreService storeService;

    // 注入BrandService
    @Autowired
    private BrandService brandService;

    // 注入ProductService
    @Autowired
    private ProductService productService;

    // 注入ProductTypeService
    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private PlaceService placeService;

    // 注入SupplyService
    @Autowired
    private SupplyService supplyService;

    // 注入UnitService
    @Autowired
    private UnitService unitService;

    @Autowired
    private TokenUtils tokenUtils;

    // 查询所有仓库
    @RequestMapping("/store-list")
    public Result storeList() {
        // 执行业务
        List<Store> storeList = storeService.queryAllStore();
        // 响应
        return Result.ok(storeList);
    }

    // 查询所有品牌
    @RequestMapping("/brand-list")
    public Result brandList() {
        List<Brand> brandList = brandService.queryAllBrand();
        return Result.ok(brandList);
    }

    // 分页查询商品
    @RequestMapping("/product-page-list")
    public Result productListPage(Page page, Product product) {
        // 执行业务
        page = productService.queryProductPage(page, product);
        // 响应
        return Result.ok(page);
    }

    // 查询所有商品分类树
    @RequestMapping("/category-tree")
    public Result loadTypeTree() {
        // 执行业务
        List<ProductType> typeTreeList = productTypeService.productTypeTree();
        // 响应
        return Result.ok(typeTreeList);
    }

    // 查询所有商品供应商
    @RequestMapping("/supply-list")
    public Result supplyList() {
        // 执行业务
        List<Supply> supplyList = supplyService.queryAllSupply();
        // 响应
        return Result.ok(supplyList);
    }

    // 查询所有产地
    @RequestMapping("/place-list")
    public Result placeList() {
        // 执行业务
        List<Place> placeList = placeService.queryAllPrice();
        // 响应
        return Result.ok(placeList);
    }

    // 查询所有单位
    @RequestMapping("/unit-list")
    public Result unitList() {
        // 执行业务
        List<Unit> unitList = unitService.queryAllUnit();
        // 响应
        return Result.ok(unitList);
    }

    // 上传商品图片
    @Value("${file.upload-path}")
    private String uploadPath;

    @CrossOrigin
    @RequestMapping("/img-upload")
    public Result uploadImage(MultipartFile file) {
        try {
            // 使用ResourceUtils解析带有类路径的uploadPath并转为File对象
            File uploadDirFile = ResourceUtils.getFile(uploadPath);
            // 拿到图片路径的绝对路径
            String uploadDirPath = uploadDirFile.getAbsolutePath();
            // 拿到图片名称
            String filename = file.getOriginalFilename();

            // 获取当前时间戳
            // long timeStamp = System.currentTimeMillis();
            // StringBuilder stringBuilder = new StringBuilder(filename);
            // stringBuilder.insert(stringBuilder.lastIndexOf("."), "-" + timeStamp);

            // 拼接完整的绝对路径
            // String uploadFilePath = uploadDirPath + "\\" + stringBuilder;
            String uploadFilePath = uploadDirPath + "\\" + filename;
            // 上传图片
            file.transferTo(new File(uploadFilePath));
            // 成功响应
            return Result.ok("图片上传成功！");
        } catch (Exception e) {
            return Result.err(Result.CODE_ERR_BUSINESS, "图片上传失败！");
        }
    }

    // 添加商品
    @RequestMapping("/product-add")
    public Result addProduct(@RequestBody Product product, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        // 获取当前登录用户id
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int createBy = currentUser.getUserId();

        product.setCreateBy(createBy);

        return productService.saveProduct(product);
    }

    // 修改商品上/下架状态
    @RequestMapping("/state-change")
    public Result changeProduct(@RequestBody Product product) {
        return productService.updateStateByPid(product);
    }

    // 删除单个商品
    @RequestMapping("/product-delete/{productId}")
    public Result deleteProduct(@PathVariable Integer productId) {
        return productService.deleteProductByIds(Arrays.asList(productId));
    }

    // 批量删除商品
    @RequestMapping("/product-list-delete")
    public Result deleteProductList(@RequestBody List<Integer> prductIdList) {
        return productService.deleteProductByIds(prductIdList);
    }

    // 修改商品
    @RequestMapping("/product-update")
    public Result updateProduct(@RequestBody Product product, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        // 获取当前用户id
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int updateBy = currentUser.getUserId();
        product.setUpdateBy(updateBy);

        return productService.setProductById(product);
    }

}
