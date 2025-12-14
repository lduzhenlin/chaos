package com.qishanor.system.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "岗位传输表")
@Data
public class PostDto {


    @Schema(description = "岗位ID")
    private Long postId;


    @NotBlank(message = "岗位编码不能为空")
    @Schema(description = "岗位编码")
    private String postCode;

    @NotBlank(message = "岗位名称不能为空")
    @Schema(description = "岗位名称")
    private String postName;

    @NotNull(message = "排序值不能为空")
    @Schema(description = "岗位排序")
    private Integer postSort;

    @Schema(description = "岗位描述")
    private String remark;
}
