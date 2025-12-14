package com.qishanor.system.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
  @description: 角色菜单表
  @author: 周振林
  @date: 2022/4/9
  
  @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
**/

@Data
@Accessors(chain = true)
public class SysRoleMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 菜单ID
	 */
	private Long menuId;

}
