package com.qishanor.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qishanor.common.excel.annotation.RequestExcel;
import com.qishanor.common.excel.annotation.ResponseExcel;
import com.qishanor.common.util.R;
import com.qishanor.system.model.SysPost;
import com.qishanor.system.model.dto.PostDto;
import com.qishanor.system.model.vo.PostExcelVO;
import com.qishanor.system.model.vo.PostVo;
import com.qishanor.system.service.SysPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 岗位信息表
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/post")
@Tag(description = "post", name = "岗位信息表管理")
@SaCheckLogin
public class SysPostController {

	private final SysPostService sysPostService;

	/**
	 * 获取岗位列表
	 */
	@GetMapping("/list")
	public R listPosts() {
		return R.ok(sysPostService.list(Wrappers.emptyWrapper()));
	}

	/**
	 * 分页查询
	 */
	@Operation(description = "分页查询", summary = "分页查询")
	@GetMapping("/page")
	@SaCheckPermission("sys_post_view")
	public R getSysPostPage(@ParameterObject Page page, String postName) {
		return R.ok(sysPostService.page(page, Wrappers.<SysPost>lambdaQuery()
			.like(StrUtil.isNotBlank(postName), SysPost::getPostName, postName)));
	}

	/**
	 * 通过id查询岗位信息表
	 */
	@Operation(description = "通过id查询", summary = "通过id查询")
	@GetMapping("/detail/{postId}")
	@SaCheckPermission("sys_post_view")
	public R getById(@PathVariable("postId") Long postId) {
		SysPost dbPost = sysPostService.getById(postId);
		PostVo vo= BeanUtil.copyProperties(dbPost, PostVo.class);
		return R.ok(vo);
	}

	/**
	 * 查询岗位信息信息
	 * @param query 查询条件
	 * @return R
	 */
	@Operation(description = "查询角色信息", summary = "查询角色信息")
	@GetMapping("/detail")
	@SaCheckPermission("sys_post_view")
	public R getDetail(@ParameterObject SysPost query) {
		SysPost dbPost=sysPostService.getOne(Wrappers.query(query));
		PostVo vo= BeanUtil.copyProperties(dbPost, PostVo.class);
		return R.ok(vo);
	}

	/**
	 * 新增岗位信息表
	 */
	@Operation(description = "新增岗位信息表", summary = "新增岗位信息表")
	@PostMapping
	@SaCheckPermission("sys_post_add")
	public R save(@RequestBody PostDto postDto) {
		SysPost post = BeanUtil.copyProperties(postDto, SysPost.class);
		return R.ok(sysPostService.save(post));
	}

	/**
	 * 修改岗位信息表
	 */
	@Operation(description = "修改岗位信息表", summary = "修改岗位信息表")
	@PutMapping
	@SaCheckPermission("sys_post_edit")
	public R updateById(@RequestBody PostDto postDto) {
		SysPost post = BeanUtil.copyProperties(postDto, SysPost.class);
		sysPostService.updateById(post);
		return R.ok();
	}

	/**
	 * 通过id删除岗位信息表
	 */
	@Operation(description = "通过id删除岗位信息表", summary = "通过id删除岗位信息表")
	@DeleteMapping
	@SaCheckPermission("sys_post_del")
	public R removeById(@RequestBody Long[] ids) {
		sysPostService.removeBatchByIds(CollUtil.toList(ids));
		return R.ok();
	}

	/**
	 * 导出excel 表格
	 */
	@ResponseExcel
	@GetMapping("/export")
	@SaCheckPermission("sys_post_export")
	public List<PostExcelVO> export(SysPost post, Long[] ids) {
		return sysPostService.listPost(post, ids);
	}

	/**
	 * 导入岗位
	 * @param excelVOList 岗位列表
	 * @param bindingResult 错误信息列表
	 * @return ok fail
	 */
	@PostMapping("/import")
	@SaCheckPermission("sys_post_export")
	public R importRole(@RequestExcel List<PostExcelVO> excelVOList, BindingResult bindingResult) {
		return sysPostService.importPost(excelVOList, bindingResult);
	}

}
