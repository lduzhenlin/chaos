package com.qishanor.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qishanor.common.util.DictVO;
import com.qishanor.common.util.R;
import com.qishanor.system.model.SysRole;
import com.qishanor.system.model.SysUserRole;
import com.qishanor.system.model.dto.RoleDto;
import com.qishanor.system.model.vo.RoleVo;
import com.qishanor.system.service.SysRoleService;
import com.qishanor.system.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 角色管理
 * @author: 周振林
 * @date: 2022-04-15
 * @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
 **/

@RestController
@RequestMapping("/admin/role")
@SaCheckLogin
public class SysRoleController {

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysUserRoleService userRoleService;

    /**
     * 获取角色列表
     * @return 角色列表
     */
    @GetMapping("/role_dict")
    public R getList()
    {
        List<DictVO> results= roleService.list(Wrappers.emptyWrapper())
                .stream().map(u->new DictVO<Integer>(u.getName(),u.getRoleId().toString())).collect(Collectors.toList());
        return R.ok(results);
    }

    @GetMapping("/user/{userId}")
    public R getRoleByUserId(@PathVariable Integer userId){
        List<Object> lists= userRoleService.listObjs(Wrappers.lambdaQuery(SysUserRole.class).select(SysUserRole::getRoleId).eq(SysUserRole::getUserId,userId));
        return R.ok(lists);
    }

    @GetMapping("/detail/{id}")
    public Object getById(@PathVariable Long id){
        SysRole role = roleService.getById(id);
        RoleVo vo= BeanUtil.copyProperties(role, RoleVo.class);
        return R.ok(vo);
    }

    /**
     * 获取角色列表
     */
    @GetMapping("/list")
    public R listRoles() {
        return R.ok(roleService.list(Wrappers.emptyWrapper()));
    }

    @GetMapping("/page")
    @SaCheckPermission("sys_role_view")
    public R page(Page page, SysRole sysRole){
        return  R.ok(roleService.page(page, Wrappers.lambdaQuery(sysRole)));
    }

    @PostMapping
    @SaCheckPermission("sys_role_add")
    public R save(@RequestBody  @Validated RoleDto dto){
        SysRole role=BeanUtil.copyProperties(dto, SysRole.class);
        roleService.save(role);
        return R.ok();
    }

    @PutMapping
    @SaCheckPermission("sys_role_edit")
    public R updateById(@RequestBody @Validated RoleDto dto){
        SysRole role=BeanUtil.copyProperties(dto, SysRole.class);
        roleService.updateById(role);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @SaCheckPermission("sys_role_del")
    public R removeById(@PathVariable Integer id){
        return R.ok(roleService.removeById(id));
    }


    /**
     * 更新角色菜单
     * @return success、false
     */
    @PutMapping("/menu")
    @SaCheckPermission("sys_role_perm")
    public R saveRoleMenus(@RequestBody RoleDto roleDto) {
        return R.ok(roleService.updateRoleMenus(roleDto.getRoleId(), roleDto.getMenuIds()));
    }

}
