package com.imooc.repository;

import com.imooc.dataobject.ProductInfo;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-07-14 11:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findByProductStatus() throws Exception {
        List<ProductInfo> byProductStatus = productInfoRepository.findByProductStatus(0);
        Assert.assertNotEquals(0,byProductStatus.size());

    }

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("223456");
        productInfo.setProductName("鸡胸肉");
        productInfo.setProductPrice(new BigDecimal(10.50));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("健身食品");
        productInfo.setProductIcon("http://www.baidu.com/1.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(3);
        ProductInfo save = productInfoRepository.save(productInfo);
        Assert.assertNotNull(save);
    }

    @Test
    public void deleteTest(){
        productInfoRepository.delete("1234567");
    }

}