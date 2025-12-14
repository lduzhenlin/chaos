package com.qishanor.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.qishanor.common.constant.CacheConstant;
import com.qishanor.common.util.R;
import com.qishanor.system.model.SysMenu;
import com.qishanor.system.model.dto.MenuDto;
import com.qishanor.system.model.vo.MenuVo;
import com.qishanor.system.service.SysMenuService;
import com.qishanor.system.service.SysRoleMenuService;
import com.qishanor.system.service.SysUserRoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description: 菜单管理
 * @author: 周振林
 * @date: 2022-04-09
 * @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
 **/
@SaCheckLogin
@RestController
@RequestMapping("/admin/menu")
public class SysMenuController {

    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleMenuService roleMenuService;
    @Autowired
    private SysMenuService menuService;

    /**
     * 返回当前用户的树形菜单集合
     *
     * @param type     类型
     * @param parentId 父节点ID
     * 查询用户所具有的 所有菜单 一次性查出
     */
    @GetMapping
    public R getUserMenu(String type, Long parentId) {
        // 获取用户菜单ids
        List<Long> menuIds= (List<Long>) StpUtil.getSession().get(CacheConstant.USER_MEMU_IDS);
        Set<SysMenu> all=menuService.list(Wrappers.<SysMenu>lambdaQuery().in(SysMenu::getMenuId, menuIds)).stream().collect(Collectors.toSet());
        List<Tree<Long>> menu=menuService.filterMenu(all, type, parentId);
        return R.ok(menu);
    }

    /**
     * 返回树形菜单集合
     *
     * @param parentId 父节点ID
     * @param menuName 菜单名称
     * 查询菜单树形结构，不指定parentId  只查询parentId=0 的第一级菜单
     */
    @GetMapping(value = "/tree")
    public R getTree(Long parentId, String menuName, String type) {
        return R.ok(menuService.treeMenu(parentId, menuName, type));
    }

    /**
     * 返回角色的菜单集合
     *
     * @param roleId 角色ID
     * @return 属性集合
     */
    @GetMapping("/tree/role/{roleId}")
    public R getRoleTree(@PathVariable Long roleId) {
        return R.ok(menuService.findMenuByRoleId(roleId).stream().map(SysMenu::getMenuId).toList());
    }

    @GetMapping("/detail/{id}")
    public R getDetail(@PathVariable Long id) {
        SysMenu menu = menuService.getById(id);
        MenuVo vo = BeanUtil.toBean(menu, MenuVo.class);
        return R.ok(vo);
    }
    /**
     * 获取详细信息
     *
     * @param query 查询条件
     */
    @GetMapping("/detail")
    public R getDetail(MenuDto query) {
        SysMenu menu=BeanUtil.copyProperties(query, SysMenu.class);
        List<SysMenu> lists = menuService.list(Wrappers.query(menu));

        List<MenuVo> results=lists.stream().map(m->{
            return BeanUtil.copyProperties(m, MenuVo.class);
        }).toList();

        return R.ok(results);
    }

    /**
     * 新增菜单
     */
    @PostMapping
    @SaCheckPermission("sys_menu_add")
    public R save(@Valid @RequestBody MenuDto menuDto) {
        SysMenu menu= BeanUtil.copyProperties(menuDto, SysMenu.class);
        menuService.save(menu);
        return R.ok();
    }

    /**
     * 更新菜单
     */
    @PutMapping
    @SaCheckPermission("sys_menu_edit")
    @CacheEvict(value = CacheConstant.MENU_DETAIL, allEntries = true)
    public R update(@Valid @RequestBody MenuDto menuDto) {
        SysMenu menu= BeanUtil.copyProperties(menuDto, SysMenu.class);
        menuService.updateById(menu);
        return R.ok();
    }

    /**
     * 删除菜单
     */
    @DeleteMapping("/{id}")
    @SaCheckPermission("sys_menu_del")
    public Object removeById(@PathVariable Long id) {
        return menuService.removeMenuById(id);
    }





}
