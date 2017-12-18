package com.imooc.repository;

import static org.junit.Assert.*;

import com.imooc.dataobject.OrderDetail;

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
 * @since 2017-07-19 10:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRespositoryTest {
    @Autowired
    private OrderDetailRespository orderDetailRespository;

    @Test
    public void findByOrderID() throws Exception {
        List<OrderDetail> orderDetailList = orderDetailRespository.findByOrderId("65464");
        Assert.assertNotEquals(0, orderDetailList.size());
    }

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId("65464");
        orderDetail.setDetailId("789");
        orderDetail.setProductIcon("http://xx.jpg");
        orderDetail.setProductId("8799789");
        orderDetail.setProductName("皮皮虾");
        orderDetail.setProductPrice(new BigDecimal(788));
        orderDetail.setProductQuantity(6);
        OrderDetail result = orderDetailRespository.save(orderDetail);
        Assert.assertNotNull(result);
    }

}