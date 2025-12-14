package com.qishanor.system.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "字典项")
public class DictItemVo {

    /**
     * 编号
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "字典项id")
    private Long itemId;

    /**
     * 所属字典类id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "所属字典类id")
    private Long dictId;

    /**
     * 数据值
     */
    @Schema(description = "数据值")
    @JsonProperty(value = "value")
    private String value;

    /**
     * 标签名
     */
    @Schema(description = "标签名")
    private String label;

    /**
     * 类型
     */
    @Schema(description = "类型")
    private String dictType;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String description;

    /**
     * 排序（升序）
     */
    @Schema(description = "排序值，默认升序")
    private Integer sortOrder;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    private String remarks;
}
