package com.qishanor.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.qishanor.common.constant.CacheConstant;
import com.qishanor.common.constant.CommonConstant;
import com.qishanor.common.enums.MenuTypeEnum;
import com.qishanor.common.util.R;
import com.qishanor.system.mapper.SysMenuMapper;
import com.qishanor.system.model.SysMenu;
import com.qishanor.system.model.SysRoleMenu;
import com.qishanor.system.service.SysMenuService;
import com.qishanor.system.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author joe
 * @since 2017-10-29
 */
@Service
@AllArgsConstructor
@Slf4j
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    private final SysRoleMenuService roleMenuService;



    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConstant.MENU_DETAIL, allEntries = true)
    public R removeMenuById(Long id) {
        // 查询父节点为当前节点的节点
        List<SysMenu> menuList = this.list(Wrappers.<SysMenu>query().lambda().eq(SysMenu::getParentId, id));
        if (CollUtil.isNotEmpty(menuList)) {
            return R.failed("当前菜单包含子菜单");
        }

        //移除角色关联菜单
        roleMenuService.remove(Wrappers.<SysRoleMenu>query().lambda().eq(SysRoleMenu::getMenuId, id));
        // 删除当前菜单及其子菜单
        this.removeById(id);
        return R.ok();
    }

    @Override
    @Cacheable(value = CacheConstant.MENU_DETAIL, key = "#roleId", unless = "#result.isEmpty()")
    public List<SysMenu> findMenuByRoleId(Long roleId) {
        // 查询角色绑定的菜单ID
        List<Long> menuIds = roleMenuService.listObjs(
                        Wrappers.lambdaQuery(SysRoleMenu.class)
                                .select(SysRoleMenu::getMenuId)
                                .eq(SysRoleMenu::getRoleId, roleId))
                .stream()
                .map(o -> (Long) o)
                .toList();

        if (CollectionUtil.isEmpty(menuIds)) {
            return Collections.emptyList();
        }
        // 根据菜单ID获取菜单详情
        List<SysMenu> lists = this.list(Wrappers.lambdaQuery(SysMenu.class).in(SysMenu::getMenuId, menuIds)).stream().toList();
        return lists;
    }


    /**
     * 构建树查询 1. 不是懒加载情况，查询全部 2. 是懒加载，根据parentId 查询 2.1 父节点为空，则查询ID -1
     * @param parentId 父节点ID
     * @param menuName 菜单名称
     * @return
     */
    @Override
    public List<Tree<Long>> treeMenu(Long parentId, String menuName, String type) {
        Long parent = parentId == null ? CommonConstant.MENU_TREE_ROOT_ID : parentId;

        List<TreeNode<Long>> collect = baseMapper
                .selectList(Wrappers.<SysMenu>lambdaQuery()
                        .eq(Objects.nonNull(parentId), SysMenu::getParentId, parentId)
                        .like(StrUtil.isNotBlank(menuName), SysMenu::getName, menuName)
                        .eq(StrUtil.isNotBlank(type), SysMenu::getMenuType, type)
                        .orderByAsc(SysMenu::getSortOrder))
                .stream()
                .map(getNodeFunction())
                .toList();

        // 模糊查询 不组装树结构 直接返回 表格方便编辑
        if (StrUtil.isNotBlank(menuName)) {
            return collect.stream().map(node -> {
                Tree<Long> tree = new Tree<>();
                tree.putAll(node.getExtra());
                BeanUtils.copyProperties(node, tree);
                return tree;
            }).toList();
        }

        return TreeUtil.build(collect, parent);
    }

    /**
     * 查询菜单
     * @param all 全部菜单
     * @param type 类型
     * @param parentId 父节点ID
     * @return
     */
    @Override
    public List<Tree<Long>> filterMenu(Set<SysMenu> all, String type, Long parentId) {

        List<TreeNode<Long>> collect = all.stream().filter(menuTypePredicate(type)).map(getNodeFunction()).toList();

        Long parent = parentId == null ? CommonConstant.MENU_TREE_ROOT_ID : parentId;
        return TreeUtil.build(collect, parent);
    }

    @NotNull
    private Function<SysMenu, TreeNode<Long>> getNodeFunction() {
        return menu -> {
            TreeNode<Long> node = new TreeNode<>();
            node.setId(menu.getMenuId());
            node.setName(menu.getName());
            node.setParentId(menu.getParentId());
            node.setWeight(menu.getSortOrder());
            // 扩展属性
            Map<String, Object> extra = new HashMap<>();
            extra.put("path", menu.getPath());
            extra.put("componentPath", menu.getComponent());
            extra.put("menuType", menu.getMenuType());
            extra.put("permission", menu.getPermission());
            extra.put("sortOrder", menu.getSortOrder());

            // 适配 vue3
            Map<String, Object> meta = new HashMap<>();
            meta.put("title", menu.getName());
            meta.put("isLink", menu.getPath() != null && menu.getPath().startsWith("http") ? menu.getPath() : "");
            meta.put("isHide", !BooleanUtil.toBooleanObject(menu.getVisible()));
            meta.put("isKeepAlive", BooleanUtil.toBooleanObject(menu.getKeepAlive()));
            meta.put("isAffix", false);
//            meta.put("isIframe", BooleanUtil.toBooleanObject(menu.getEmbedded()));
            meta.put("icon", menu.getIcon());

            extra.put("meta", meta);
            node.setExtra(extra);
            return node;
        };
    }

    /**
     * menu 类型断言
     * @param type 类型
     * @return Predicate
     */
    private Predicate<SysMenu> menuTypePredicate(String type) {
        return vo -> {
            if (MenuTypeEnum.TOP_MENU.getDescription().equals(type)) {
                return MenuTypeEnum.TOP_MENU.getType().equals(vo.getMenuType());
            }
            // 其他查询 左侧 + 顶部
            return !MenuTypeEnum.BUTTON.getType().equals(vo.getMenuType());
        };
    }

}

