package com.qishanor.system.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;

import com.qishanor.common.util.R;
import com.qishanor.system.model.SysDept;
import com.qishanor.system.model.vo.DeptExcelVo;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * <p>
 * 部门管理 服务类
 * </p>
 *
 * @author joe
 * @since 2018-01-20
 */
public interface SysDeptService extends IService<SysDept> {

	/**
	 * 查询部门树菜单
	 * @param deptName 部门名称
	 * @return 树
	 */
	List<Tree<Long>> selectTree(String deptName, Long parentId);

	/**
	 * 删除部门
	 * @param id 部门 ID
	 * @return 成功、失败
	 */
	Boolean removeDeptById(Long id);

	List<DeptExcelVo> exportDeptExcel();

	R importDeptExcel(List<DeptExcelVo> excelVOList);

	/**
	 * 获取部门的所有后代部门列表
	 * @param deptId 部门ID
	 * @return 后代部门列表
	 */
	List<SysDept> listDescendant(Long deptId);

	/**
	 * 获取部门负责人
	 * @param deptId deptId
	 * @return user id list
	 */
//	List<Long> listDeptLeader(Long deptId);

	/**
	 * 查询全部部门包含用户
	 * @param parentDeptId 父部门ID
	 * @param type 查询类型
	 */
//	Map<String, Object> listOrgTree(Long parentDeptId, String type);

	/**
	 * 根据用户名模糊搜索用户
	 * @param username 用户名
	 * @return List user
	 */
//	List<OrgTreeVO> getOrgTreeUser(String username);

}
