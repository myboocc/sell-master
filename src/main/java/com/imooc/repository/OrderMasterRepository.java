package com.imooc.repository;

import com.imooc.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单
 * Created by hongcj
 * 2017/7/18 22:33.
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    //根据openID获取商品列表（分页）
    Page<OrderMaster> findByBuyerOpenid(String buyOpenid, Pageable pageable);
}
