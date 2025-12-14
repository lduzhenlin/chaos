package com.qishanor.system.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;

/**
 * 用户部门关联表实体类
 */
@Data
@TableName("sys_user_dept")
@EqualsAndHashCode(callSuper = true)
public class SysUserDept extends Model<SysUserDept> {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@Schema(description = "用户id")
	private Long userId;

	/**
	 * 部门ID
	 */
	@Schema(description = "部门id")
	private Long deptId;


}







