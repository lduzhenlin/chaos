
package com.qishanor.system.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author joe
 * @since 2017-10-29
 */
@Data
//@TenantTable
//@Schema(description = "角色")
@EqualsAndHashCode(callSuper = true)
public class SysRole extends Model<SysRole> {

	private static final long serialVersionUID = 1L;

	@JsonSerialize(using = ToStringSerializer.class)
	@TableId(value = "role_id", type = IdType.ASSIGN_ID)
//	@Schema(description = "角色编号")
	private Long roleId;

	@NotBlank(message = "角色名称不能为空")
//	@Schema(description = "角色名称")
	private String name;

	@NotBlank(message = "角色标识不能为空")
//	@Schema(description = "角色标识")
	private String code;

//	@Schema(description = "角色描述")
	private String description;

	@NotNull(message = "数据权限类型不能为空")
//	@Schema(description = "数据权限类型")
	private Integer dsType;

	/**
	 * 数据权限作用范围
	 */
//	@Schema(description = "数据权限作用范围")
	private String dsScope;

	/**
	 * 创建人
	 */
	@TableField(fill = FieldFill.INSERT)
//	@Schema(description = "创建人")
	private String createBy;

	/**
	 * 修改人
	 */
	@TableField(fill = FieldFill.UPDATE)
//	@Schema(description = "修改人")
	private String updateBy;

	/**
	 * 创建时间
	 */
//	@Schema(description = "创建时间")
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
//	@Schema(description = "修改时间")
	@TableField(fill = FieldFill.UPDATE)
	private LocalDateTime updateTime;

	/**
	 * 删除标识（0-正常,1-删除）
	 */
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
//	@Schema(description = "删除标记,1:已删除,0:正常")
	private String delFlag;

}
