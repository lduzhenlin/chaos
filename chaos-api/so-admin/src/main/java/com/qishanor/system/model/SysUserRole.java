package com.qishanor.system.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
  @description: 用户角色表
  @author: 周振林
  @date: 2022/4/9
  
  @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
**/

@Data
@Accessors(chain = true)
public class SysUserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 角色ID
	 */
	private Long roleId;

}
