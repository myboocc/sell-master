package com.imooc.repository;

import com.imooc.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 订单商品
 * Created by hongcj
 * 2017/7/18 23:01.
 */
public interface OrderDetailRespository extends JpaRepository<OrderDetail,String>{
    //根据订单ID获取订单详情
    List<OrderDetail> findByOrderId(String orderId);
}
