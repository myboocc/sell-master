package com.imooc.repository;

import static org.junit.Assert.*;

import com.imooc.dataobject.ProductCategory;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-07-12 15:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOne(){
        ProductCategory one = productCategoryRepository.findOne(1);
        System.out.println(one.toString());
    }

    @Test
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory("测试",3);
        productCategoryRepository.save(productCategory);
    }

    @Test
    public void updateTest(){
        ProductCategory productCategory = productCategoryRepository.findOne(2);
        productCategory.setCategoryName("男生最爱7");
        productCategoryRepository.save(productCategory);
    }

    @Test
    public void getByCategoryTypeList(){
        List<Integer> categoryTypeList = new ArrayList<Integer>();
        categoryTypeList.add(2);
        categoryTypeList.add(3);
        List<ProductCategory> byCategoryTypeIn = productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
        Assert.assertNotNull(byCategoryTypeIn);
    }


}