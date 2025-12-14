package com.qishanor.system.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "部门编辑")
public class DeptDto {

    private Long deptId;

    @Schema(description = "父级部门id")
    private Long parentId;

    @NotBlank(message = "部门名称不能为空")
    @Schema(description = "部门名称")
    private String name;

    @NotNull(message = "排序值不能为空")
    @Schema(description = "排序值")
    private Integer sortOrder;
}
