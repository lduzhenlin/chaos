package com.qishanor.system.mapper;

/**
 * @description:
 * @author: 周振林
 * @date: 2022-04-08
 * 
 * @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
 **/

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qishanor.system.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}
