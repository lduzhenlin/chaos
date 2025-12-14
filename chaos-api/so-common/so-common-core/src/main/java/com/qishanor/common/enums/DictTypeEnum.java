package com.qishanor.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 字典类型
 * @author: 周振林
 * @date: 2022-04-12
 * 
 * @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
 **/

@Getter
@AllArgsConstructor
public enum DictTypeEnum {

    /**
     * 字典类型-系统内置（不可修改）
     */
    SYSTEM("1", "系统内置"),

    /**
     * 字典类型-业务类型
     */
    BIZ("0", "业务类");

    /**
     * 类型
     */
    private String type;

    /**
     * 描述
     */
    private String description;
}
