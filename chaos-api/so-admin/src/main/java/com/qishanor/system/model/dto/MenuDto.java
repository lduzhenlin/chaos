package com.qishanor.system.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MenuDto implements Serializable {

    private Long menuId;
    private String menuType;
    private Long parentId;
    private String name;
    private String permission;
    private String path;
    private String icon;
    private Integer sortOrder;
    private String visible;

    /**
     * 路由缓冲
     */
    private String keepAlive;

}
