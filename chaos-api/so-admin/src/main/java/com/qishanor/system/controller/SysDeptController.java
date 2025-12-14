package com.qishanor.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import com.qishanor.common.util.R;
import com.qishanor.system.model.SysDept;
import com.qishanor.system.model.dto.DeptDto;
import com.qishanor.system.model.vo.DeptExcelVo;
import com.qishanor.system.service.SysDeptService;
import com.qishanor.common.excel.annotation.RequestExcel;
import com.qishanor.common.excel.annotation.ResponseExcel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 部门管理
 * @author: 周振林
 * @date: 2022-04-12
 * @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
 **/

@RestController
@RequestMapping("/admin/dept")
public class SysDeptController {

    @Autowired
    private SysDeptService deptService;



    /**
     * 查询全部部门
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(deptService.list());
    }

    /**
     * 返回树形菜单集合
     * @param deptName 部门名称
     * @return 树形菜单
     */
    @GetMapping(value = "/tree")
    public R getTree(String deptName, Long parentId) {
        return R.ok(deptService.selectTree(deptName, parentId));
    }


    @GetMapping("/{id}")
    public R getById(@PathVariable Long id) {
        return R.ok(deptService.getById(id));
    }

    /**
     * 添加
     */
    @PostMapping
    @SaCheckPermission("sys_dept_add")
    public R save(@Valid @RequestBody DeptDto deptDto) {
        SysDept dept= BeanUtil.copyProperties(deptDto, SysDept.class);
        return R.ok(deptService.save(dept));
    }

    @PutMapping
    @SaCheckPermission("sys_dept_edit")
    public R update(@Valid @RequestBody DeptDto deptDto) {
        SysDept dept= BeanUtil.copyProperties(deptDto, SysDept.class);
        return R.ok(deptService.updateById(dept));
    }


    @DeleteMapping("/{id}")
    @SaCheckPermission("sys_dept_del")
    public R removeById(@PathVariable Long id) {
        return R.ok(deptService.removeDeptById(id));
    }


    /**
     * 查收子级列表
     * @return 返回子级
     */
    @GetMapping(value = "/getDescendantList/{deptId}")
    public R getDescendantList(@PathVariable Long deptId) {
        return R.ok(deptService.listDescendant(deptId));
    }

    /**
     * 导入部门
     */
    @PostMapping("import")
    public R importDept(@RequestExcel List<DeptExcelVo> excelVOList) {
        return deptService.importDeptExcel(excelVOList);
    }

/**
 * 导出部门
 * @return
 */
    @ResponseExcel
    @GetMapping("/export")
    public List<DeptExcelVo> export() {
        return deptService.exportDeptExcel();
    }






    @GetMapping(value = "/leader/{deptId}")
    public R getAllDeptLeader(@PathVariable Long deptId) {
//        return R.ok(deptService.listDeptLeader(deptId));
        return R.ok();
    }


    /**
     * 查询全部部门包含用户
     * @param parentDeptId 父部门ID
     * @param type 查询类型
     */
    @GetMapping("/org")
    public R listOrgTree(Long parentDeptId, String type) {
//        return R.ok(deptService.listOrgTree(parentDeptId, type));
        return R.ok();
    }

    /**
     * 模糊搜索用户
     * @param username 用户名/拼音/首字母
     * @return 匹配到的用户
     */
    @GetMapping("/org/user/search")
    public R getOrgTreeUser(@RequestParam String username) {
//        return R.ok(deptService.getOrgTreeUser(username));
        return R.ok();
    }
}
