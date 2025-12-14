package com.qishanor.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qishanor.system.model.SysRoleMenu;

/**
 * @description: 角色菜单表 服务类
 * @author: 周振林
 * @date: 2022-04-09
 * @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
 **/

public interface SysRoleMenuService extends IService<SysRoleMenu> {

    Boolean saveRoleMenus(Long roleId, String menuIds);
}
