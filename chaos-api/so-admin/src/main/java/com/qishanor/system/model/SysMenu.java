package com.qishanor.system.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
  @description: 菜单表
  @author: 周振林
  @date: 2022/4/9

  @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
**/

@Data
@Accessors(chain = true)
public class SysMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(type= IdType.AUTO)
	private Long menuId;

	/**
	 * 父菜单ID
	 */
	private Long parentId;

	/**
	 * 菜单名称
	 */
	private String name;

	/**
	 * 菜单权限标识
	 */
	private String permission;

	/**
	 * 前端路由标识路径
	 */
	private String path;

	/**
	 * 前端组件路径
	 */
	private String component;
	/**
	 * 图标
	 */
	private String icon;

	/**
	 * 菜单类型 （0菜单 1按钮）
	 */
	private String menuType;

	/**
	 * 排序值
	 */
	private Integer sortOrder;
	//是否可见
	private String visible;

	/**
	 * 路由缓冲
	 */
	private String keepAlive;


	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	@TableField(fill = FieldFill.INSERT)
	private Integer createBy;

	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Integer updateBy;

	/**
	 * 删除标记 1:已删除,0:正常
	 */
	private Integer delFlag;
}
