package com.qishanor.service;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.hutool.db.DbUtil;
import com.qishanor.common.util.SpringContextHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: Sa-Token自定义权限验证接口扩展
 **/
//@Component
public class StpInterfaceImpl implements StpInterface {

    /**
     * 获取数据源并返回 Db 对象
     */
    private Db getDb() {
        DataSource dataSource = SpringContextHolder.getBean(DataSource.class);
        return DbUtil.use(dataSource);
    }

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        try {
            Db db = getDb();
            Long userId = Long.valueOf(loginId.toString());

            // 通过用户ID 获取到角色ID
            List<Integer> roleIds = db.query("SELECT role_id FROM sys_user_role WHERE user_id = ?", userId)
                    .stream()
                    .map(record -> record.getInt("role_id"))
                    .collect(Collectors.toList());

            if (CollUtil.isEmpty(roleIds)) {
                return new ArrayList<>();
            }

            // 通过角色ID 获取到菜单ID
            String roleIdPlaceholders = roleIds.stream()
                    .map(id -> "?")
                    .collect(Collectors.joining(","));
            String menuIdSql = "SELECT menu_id FROM sys_role_menu WHERE role_id IN (" + roleIdPlaceholders + ")";
            List<Integer> menuIds = db.query(menuIdSql, roleIds.toArray())
                    .stream()
                    .map(record -> record.getInt("menu_id"))
                    .distinct()
                    .collect(Collectors.toList());

            if (CollUtil.isEmpty(menuIds)) {
                return new ArrayList<>();
            }

            // 通过菜单ID 查询所有权限
            String menuIdPlaceholders = menuIds.stream()
                    .map(id -> "?")
                    .collect(Collectors.joining(","));
            String permissionSql = "SELECT permission FROM sys_menu WHERE menu_id IN (" + menuIdPlaceholders + ") AND (del_flag = 0 OR del_flag IS NULL)";
            List<String> permissions = db.query(permissionSql, menuIds.toArray())
                    .stream()
                    .map(record -> record.getStr("permission"))
                    .filter(StrUtil::isNotBlank)
                    .collect(Collectors.toList());

            return permissions;
        } catch (SQLException e) {
            throw new RuntimeException("获取权限列表失败", e);
        }
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        try {
            Db db = getDb();
            Long userId = Long.valueOf(loginId.toString());

            // 通过用户ID 查询角色ID
            List<Integer> roleIds = db.query("SELECT role_id FROM sys_user_role WHERE user_id = ?", userId)
                    .stream()
                    .map(record -> record.getInt("role_id"))
                    .collect(Collectors.toList());

            if (CollUtil.isEmpty(roleIds)) {
                return new ArrayList<>();
            }

            // 通过角色ID 查询角色编码
            String roleIdPlaceholders = roleIds.stream()
                    .map(id -> "?")
                    .collect(Collectors.joining(","));
            String roleSql = "SELECT code FROM sys_role WHERE role_id IN (" + roleIdPlaceholders + ") AND (del_flag = 0 OR del_flag IS NULL)";
            List<String> roles = db.query(roleSql, roleIds.toArray())
                    .stream()
                    .map(record -> record.getStr("code"))
                    .filter(StrUtil::isNotBlank)
                    .collect(Collectors.toList());

            return roles;
        } catch (SQLException e) {
            throw new RuntimeException("获取角色列表失败", e);
        }
    }
}