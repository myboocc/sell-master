package com.imooc.service.impl;

import static org.junit.Assert.*;

import com.imooc.dataobject.OrderDetail;
import com.imooc.dto.CartDTO;
import com.imooc.dto.OrderDTO;

import java.util.ArrayList;
import java.util.List;

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
 * TODO
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-07-19 14:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;

    private final static String BUYER_OPENID = "678998678";

    @Test
    public void create() throws Exception {
//        name: "张三"
//            * phone: "18868822111"
//            * address: "慕课网总部"
//            * openid: "ew3euwhd7sjw9diwkq" //用户的微信openid
//            * items: [{
//     * productId: "1423113435324",
//     * productQuantity: 2 //购买数量
//                * }]


        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("汤普森");
        orderDTO.setBuyerPhone("15678900987");
        orderDTO.setBuyerAddress("甲骨文球场");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车信息
        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

        OrderDetail orderDetailOne = new OrderDetail();
        orderDetailOne.setProductId("123456");
        orderDetailOne.setProductQuantity(10);

        OrderDetail orderDetailTwo = new OrderDetail();
        orderDetailTwo.setProductId("123457");
        orderDetailTwo.setProductQuantity(16);

        orderDetails.add(orderDetailOne);
        orderDetails.add(orderDetailTwo);
        orderDTO.setOrderDetailList(orderDetails);

        OrderDTO result = orderService.create(orderDTO);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() throws Exception {
        OrderDTO result = orderService.findOne("1500992569458388240");
        Assert.assertNotNull(result);
    }

    @Test
    public void findList() throws Exception {
        Page<OrderDTO> list = orderService.findList(BUYER_OPENID, new PageRequest(0, 10));
        Assert.assertNotEquals(0,list.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
        OrderDTO result = orderService.findOne("1500451201853203101");
        OrderDTO cancel = orderService.cancel(result);
        Assert.assertNotNull(cancel);
    }

    @Test
    public void finish() throws Exception {
        OrderDTO result = orderService.findOne("1500451201853203101");
        OrderDTO finish = orderService.finish(result);
        Assert.assertNotNull(finish);
    }

    @Test
    public void paid() throws Exception {
        OrderDTO result = orderService.findOne("1500451201853203101");
        OrderDTO paid = orderService.paid(result);
        Assert.assertNotNull(paid);
    }

}