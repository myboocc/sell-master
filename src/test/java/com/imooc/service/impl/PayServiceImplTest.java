package com.imooc.service.impl;

import static org.junit.Assert.*;

import com.imooc.dto.OrderDTO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-08-10 10:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {
    @Autowired
    private PayServiceImpl payService;

    @Autowired
    private OrderServiceImpl orderService;
    @Test
    public void create() throws Exception {
        OrderDTO dto = this.orderService.findOne("1500451201853203101");
        payService.create(dto);
    }

}