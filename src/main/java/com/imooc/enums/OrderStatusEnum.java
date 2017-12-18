package com.imooc.enums;

import lombok.Getter;

/**
 * 订单状态
 * Created by hongcj
 * 2017/7/18 22:46.
 */
@Getter
public enum OrderStatusEnum implements CodeEnum{
    NEW(0,"新订单"),
    FINISHED(1,"已完结"),
    CANCEL(2,"已取消"),
        ;


    private Integer code;

    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
