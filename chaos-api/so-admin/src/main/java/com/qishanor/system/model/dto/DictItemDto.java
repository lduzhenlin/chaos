package com.qishanor.system.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class DictItemDto implements Serializable {

    private Long itemId;

    @NotNull(message = "字典类型不能为空")
    @Schema(description = "所属字典类id")
    private Long dictId;

    @NotBlank(message = "字典值不能为空")
    @Schema(description = "数据值")
    @JsonProperty(value = "value")
    private String value;

    @Schema(description = "标签名")
    private String label;

    @Schema(description = "描述")
    private String description;

    @NotNull(message = "排序不能为空")
    @Schema(description = "排序值，默认升序")
    private Integer sortOrder;

    @Schema(description = "备注信息")
    private String remarks;
}
