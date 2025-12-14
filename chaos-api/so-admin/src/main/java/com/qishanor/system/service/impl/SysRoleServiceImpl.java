package com.qishanor.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qishanor.system.mapper.SysRoleMapper;
import com.qishanor.system.model.SysRole;
import com.qishanor.system.service.SysRoleMenuService;
import com.qishanor.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 用户角色表 服务实现类
 * @author: 周振林
 * @date: 2022-04-09
 * @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
 **/

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public Boolean updateRoleMenus(Long roleId, String menuIds) {
        return sysRoleMenuService.saveRoleMenus(roleId,menuIds);
    }
}
