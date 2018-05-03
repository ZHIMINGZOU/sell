package com.ming.sell.Controller;

import com.ming.sell.VO.ProductInfoVO;
import com.ming.sell.VO.ProductVO;
import com.ming.sell.VO.ResultVO;
import com.ming.sell.pojo.ProductCategory;
import com.ming.sell.pojo.ProductInfo;
import com.ming.sell.service.CategoryService;
import com.ming.sell.service.ProductService;
import com.ming.sell.utils.ResultVOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list() {

        /**查询所有的上架商品*/
        List<ProductInfo> productInfoList = productService.findUpAll();

        /**查询类目（一次性查询）*/
        /*传统方法
        List<Integer> categoryTypeList = new ArrayList();
        for (ProductInfo productInfo : productInfoList) {
            categoryTypeList.add(productInfo.getCategoryType());
        }
        */
        /*java8方法（lambda）*/
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //数据拼装
        List<ProductVO> productVOList = new ArrayList<>();  //json第二层（name、type、foods）
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();//json最里层（id、name、price、description、icon）
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);//复制
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        /*
        ResultVO resultVO = new ResultVO(); //json最外层（code、msg、data）
        resultVO.setData(productVOList);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    */
        return ResultVOUtils.success(productVOList);  //Utils
    }
}
