package com.qishanor.system.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @description:
 * @author: 周振林
 * @date: 2022-04-17
 * 
 * @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
 **/
@Data
public class RoleDto {

    private Long roleId;

    private String menuIds;

    @NotBlank(message = "角色名称不能为空")
    @Schema(description = "角色名称")
    private String name;

    @NotBlank(message = "角色标识不能为空")
    @Schema(description = "角色标识")
    private String code;

    @Schema(description = "角色描述")
    private String description;

    @NotNull(message = "数据权限类型不能为空")
    @Schema(description = "数据权限类型")
    private Integer dsType;

    @NotNull(message = "数据权限作用范围不能为空")
    @Schema(description = "数据权限作用范围")
    private String dsScope;
}
