package com.qishanor.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qishanor.system.model.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @description: 菜单表 Mapper接口
 * @author: 周振林
 * @date: 2022-04-09
 * 
 * @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
 **/

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
}
