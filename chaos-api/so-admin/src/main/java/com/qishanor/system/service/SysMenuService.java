package com.qishanor.system.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qishanor.common.util.R;
import com.qishanor.system.model.SysMenu;

import java.util.List;
import java.util.Set;

/**
 * @description: 菜单表 服务类
 * @author: 周振林
 * @date: 2022-04-09
 * @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
 **/

public interface SysMenuService extends IService<SysMenu> {



    /**
     * 级联删除菜单
     * @param id 菜单ID
     * @return 成功、失败
     */
    R removeMenuById(Long id);


    /**
     * 通过角色编号查询URL 权限
     * @param roleId 角色ID
     * @return 菜单列表
     */
    List<SysMenu> findMenuByRoleId(Long roleId);

    /**
     * 构建树
     * @param parentId 父节点ID
     * @param menuName 菜单名称
     * @return
     */
    List<Tree<Long>> treeMenu(Long parentId, String menuName, String type);

    /**
     * 查询菜单
     * @param voSet
     * @param parentId
     * @return
     */
    List<Tree<Long>> filterMenu(Set<SysMenu> voSet, String type, Long parentId);

}
