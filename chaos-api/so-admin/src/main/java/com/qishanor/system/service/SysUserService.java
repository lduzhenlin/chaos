package com.qishanor.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qishanor.system.model.SysUser;
import com.qishanor.system.model.dto.UserDto;
import com.qishanor.system.model.dto.UserQueryDto;
import com.qishanor.system.model.vo.UserLoginVo;
import com.qishanor.system.model.vo.UserVo;

/**
 * @description: 用户表 服务类
 * @author: 周振林
 * @date: 2022-04-08
 * @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
 **/
public interface SysUserService extends IService<SysUser> {

    UserLoginVo login(String username, String password);

    UserLoginVo getLoginUserInfo();


    /**
     * 获取用户详情（包含角色、岗位、部门列表）
     * @param id 用户ID
     * @return UserVO
     */
    UserVo getUserDetailById(Long id);

    UserVo getUserDetail(UserQueryDto userQueryDto);

    void saveUser(UserDto userDto);

    void updateUser(UserDto userDto);

    /**
     * 分页查询用户VO列表（包含部门、角色、岗位信息）
     * @param page 分页参数
     * @param query 查询条件
     * @return 分页结果
     */
    IPage<UserVo> page(Page page, SysUser query);


}
