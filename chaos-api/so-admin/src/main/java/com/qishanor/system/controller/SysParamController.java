package com.qishanor.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qishanor.common.util.R;
import com.qishanor.system.model.SysParam;
import com.qishanor.system.service.SysParamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 公共参数配置表
 *
 * @author Joe
 * @date 2022-06-06 20:18:04
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sys/param" )
public class SysParamController {

    private final  SysParamService sysParamService;

    /**
     * 分页查询
     */
    @GetMapping("/page" )
    @SaCheckPermission("sys_param_view" )
    public R getSysParamPage(Page page, SysParam sysParam) {
        return R.ok(sysParamService.page(page, Wrappers.query(sysParam)));
    }


    @GetMapping("/{id}" )
    @SaCheckPermission("sys_param_view" )
    public R getById(@PathVariable("id" ) Integer id) {
        return R.ok(sysParamService.getById(id));
    }


    @PostMapping
    @SaCheckPermission("sys_param_add" )
    public R save(@RequestBody SysParam sysParam) {
        return R.ok(sysParamService.save(sysParam));
    }


    @PutMapping
    @SaCheckPermission("sys_param_edit" )
    public R updateById(@RequestBody SysParam sysParam) {
        return R.ok(sysParamService.updateById(sysParam));
    }


    @DeleteMapping("/{id}" )
    @SaCheckPermission("sys_param_del" )
    public R removeById(@PathVariable Integer id) {
        return R.ok(sysParamService.removeById(id));
    }


}
