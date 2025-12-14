package com.qishanor.system.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrgTreeVo implements Serializable {

	/**
	 * 用户od
	 */
	private Long id;

	/**
	 * 用户名称
	 */
	private String name;

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 选择
	 */
	private Boolean selected;

	/**
	 * 头像
	 */
	private String avatar;

}
