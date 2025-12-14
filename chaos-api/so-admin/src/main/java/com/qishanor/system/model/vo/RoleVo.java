package com.qishanor.system.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "角色")
public class RoleVo implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;

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

    /**
     * 数据权限作用范围
     */
	@Schema(description = "数据权限作用范围")
    private String dsScope;
}
