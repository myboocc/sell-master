package com.imooc.repository;

import static org.junit.Assert.*;

import com.imooc.dataobject.OrderMaster;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-07-19 10:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterRepositoryTest {
    @Autowired
    private  OrderMasterRepository orderMasterRepository;

    @Test
    public void save(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("67789");
        orderMaster.setBuyerAddress("速贷中心");
        orderMaster.setBuyerName("詹姆斯");
        orderMaster.setBuyerOpenid("78079878");
        orderMaster.setBuyerPhone("15057586708");
        orderMaster.setOrderAmount(new BigDecimal(400));
        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne(){
        String orderId = "67789";
        OrderMaster one = orderMasterRepository.findOne(orderId);
        log.info("订单列表: order={}",one);
        Assert.assertNotNull(one);
    }

    @Test
    public void findAll(){
        List<OrderMaster> all = orderMasterRepository.findAll();
        Assert.assertNotEquals(0,all.size());
    }

    @Test
    @Transactional
    public void delete(){
        String orderId = "67789";
        orderMasterRepository.delete(orderId);
    }


    @Test
    public void findByBuyerOpenid(){
        String buyOpenId = "78079878";
        PageRequest pageRequest = new PageRequest(0,10);
        Page<OrderMaster> page = orderMasterRepository.findByBuyerOpenid(buyOpenId,pageRequest);
        Assert.assertNotEquals(0,page.getTotalElements());
    }
}