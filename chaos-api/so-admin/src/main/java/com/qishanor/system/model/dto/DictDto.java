package com.qishanor.system.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "字典类型")
@Data
public class DictDto {

    @Schema(description = "字典编号")
    private Long dictId;

    @Schema(description = "是否系统内置")
    private String systemFlag;

    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "字典描述")
    private String description;

    @Schema(description = "备注信息")
    private String remarks;
}
