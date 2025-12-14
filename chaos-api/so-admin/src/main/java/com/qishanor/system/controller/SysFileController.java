package com.qishanor.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.qishanor.common.util.R;
import com.qishanor.system.model.SysFile;
import com.qishanor.system.service.SysFileService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



/**
 * 文件管理表
 *
 * @author Joe
 * @date 2022-06-22 15:56:09
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sys/file" )
public class SysFileController {

    private   SysFileService sysFileService;

    /**
     * 上传文件 文件名采用uuid,避免原始文件名中带"-"符号导致下载的时候解析出现异常
     * @param file 资源
     * @return R(/ admin / bucketName / filename)
     */
    @PostMapping(value = "/upload")
    public R upload(@RequestPart("file") MultipartFile file) {
//        return sysFileService.uploadFile(file);
        return null;
    }

    /**
     * 获取文件
     */
    @GetMapping("/{bucket}/{fileName}")
    public void file(@PathVariable String bucket, @PathVariable String fileName, HttpServletResponse response) {
//        sysFileService.getFile(bucket, fileName, response);
    }

    /**
     * 分页查询
     */
    @GetMapping("/page" )
    @SaCheckPermission("sys_file_view" )
    public R getSysFilePage(Page page, SysFile sysFile) {
        return R.ok(sysFileService.page(page, Wrappers.query(sysFile)));
    }


    @GetMapping("/{id}" )
    @SaCheckPermission("sys_file_view" )
    public R getById(@PathVariable("id" ) Long id) {
        return R.ok(sysFileService.getById(id));
    }


    @PostMapping
    @SaCheckPermission("sys_file_add" )
    public R save(@RequestBody SysFile sysFile) {
        return R.ok(sysFileService.save(sysFile));
    }


    @PutMapping
    @SaCheckPermission("sys_file_edit" )
    public R updateById(@RequestBody SysFile sysFile) {
        return R.ok(sysFileService.updateById(sysFile));
    }


    @DeleteMapping("/{id}" )
    @SaCheckPermission("sys_file_del" )
    public R removeById(@PathVariable String id) {
        return R.ok(sysFileService.removeById(id));
    }



}
