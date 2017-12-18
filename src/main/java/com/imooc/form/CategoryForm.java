package com.imooc.form;

import lombok.Data;

/**
 * Created by hongcj
 * 2017-08-21 10:43
 */
@Data
public class CategoryForm {

    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
}
