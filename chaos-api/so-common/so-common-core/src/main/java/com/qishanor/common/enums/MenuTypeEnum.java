package com.qishanor.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 菜单类型
 * @author: 周振林
 * @date: 2022-04-10
 * 
 * @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
 **/

@Getter
@AllArgsConstructor
public enum MenuTypeEnum {

    /**
     * 左侧菜单
     */
    LEFT_MENU("0", "left"),

    /**
     * 顶部菜单
     */
    TOP_MENU("2", "top"),

    /**
     * 按钮
     */
    BUTTON("1", "button");

    /**
     * 类型
     */
    private String type;

    /**
     * 描述
     */
    private String description;

}