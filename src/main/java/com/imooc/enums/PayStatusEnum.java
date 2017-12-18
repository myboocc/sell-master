package com.imooc.enums;

import lombok.Getter;

/**
 * 支付状态
 * Created by hongcj
 * 2017/7/18 22:55.
 */
@Getter
public enum PayStatusEnum implements CodeEnum{
    WAIT(0,"等待支付"),
    SUCCESS(1, "支付成功"),
    ;
    private Integer code;

    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}


