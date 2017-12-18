/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.imooc.controller;

import com.imooc.VO.ProductInfoVO;
import com.imooc.VO.ProductVO;
import com.imooc.VO.ResultVO;
import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductInfoServcie;
import com.imooc.utils.ResultVOUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 买家商品
 * @author hongcj
 * @version V1.0
 * @since 2017-07-17 11:03
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductInfoServcie productInfoServcie;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("testRedisCache")
    @Cacheable(cacheNames = "product",key = "#sellerId",condition = "#sellerId.length() > 3", unless = "#result.getCode() != 0")
    public ResultVO TestRedisCache(@RequestParam("sellerId") String sellerId){
        //1.查询所有上架的商品
        List<ProductInfo> productInfoList = productInfoServcie.findUpAll();
        //2. 查询出所有的类目

        /**
         * 这里有两种方法，一种是传统的方法-使用for循环遍历，一种是java8 的lambdas写法
         */

        //for循环遍历的方式
//        List<Integer> categoryTypeList = new ArrayList<>();
//        for(ProductInfo productInfo: productInfoList){
//            categoryTypeList.add(productInfo.getCategoryType());
//        }

        //java8 的lambdas写法
        List<Integer> categoryTypeList = productInfoList.stream()
            .map(e -> e.getCategoryType())
            .collect(Collectors.toList());

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3.拼装成前端需要的数据
        List<ProductVO> productVOList = new ArrayList<ProductVO>();
        ProductVO productVO = null;
        for (ProductCategory category: productCategoryList){
            productVO = new ProductVO();
            productVO.setCategoryname(category.getCategoryName());
            productVO.setCategoryType(category.getCategoryType());
            ArrayList<ProductInfoVO> productInfoVOList= new ArrayList<ProductInfoVO>();
            //遍历上架商品
            for (ProductInfo productInfo: productInfoList){
                //判断商品是不是属于该类目
                if(productInfo.getCategoryType().equals(category.getCategoryType())){
                    //将该商品加到该类目属性下
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }


    @GetMapping("list")
    public ResultVO List(){
        //1.查询所有上架的商品
        List<ProductInfo> productInfoList = productInfoServcie.findUpAll();
        //2. 查询出所有的类目

        /**
         * 这里有两种方法，一种是传统的方法-使用for循环遍历，一种是java8 的lambdas写法
         */

        //for循环遍历的方式
        //        List<Integer> categoryTypeList = new ArrayList<>();
        //        for(ProductInfo productInfo: productInfoList){
        //            categoryTypeList.add(productInfo.getCategoryType());
        //        }

        //java8 的lambdas写法
        List<Integer> categoryTypeList = productInfoList.stream()
            .map(e -> e.getCategoryType())
            .collect(Collectors.toList());

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //3.拼装成前端需要的数据
        List<ProductVO> productVOList = new ArrayList<ProductVO>();
        ProductVO productVO = null;
        for (ProductCategory category: productCategoryList){
            productVO = new ProductVO();
            productVO.setCategoryname(category.getCategoryName());
            productVO.setCategoryType(category.getCategoryType());
            ArrayList<ProductInfoVO> productInfoVOList= new ArrayList<ProductInfoVO>();
            //遍历上架商品
            for (ProductInfo productInfo: productInfoList){
                //判断商品是不是属于该类目
                if(productInfo.getCategoryType().equals(category.getCategoryType())){
                    //将该商品加到该类目属性下
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }
}
