package com.qishanor.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qishanor.common.constant.CacheConstant;
import com.qishanor.common.exception.BizException;
import com.qishanor.system.mapper.*;
import com.qishanor.system.model.*;
import com.qishanor.system.model.constant.UserStateEnum;
import com.qishanor.system.model.dto.UserDto;
import com.qishanor.system.model.dto.UserQueryDto;
import com.qishanor.system.model.vo.UserLoginVo;
import com.qishanor.system.model.vo.UserVo;
import com.qishanor.system.service.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: 用户表 服务实现类
 * @author: 周振林
 * @date: 2022-04-08
 * @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
 **/

@Data
@RequiredArgsConstructor
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {



    @Autowired
    private SysDeptService deptService;

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysPostService postService;

    @Autowired
    private SysUserDeptMapper userDeptMapper;

    @Autowired
    private SysUserPostMapper userPostMapper;

    private final SysUserRoleMapper userRoleMapper;

    private final SysRoleMenuMapper roleMenuMapper;


    @Override
    public UserLoginVo login(String username, String password) {
        // 查询用户
        SysUser dbUser = baseMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, username));

        // BCrypt密码验证
        if (ObjectUtil.isEmpty(dbUser) || !BCrypt.checkpw(password, dbUser.getPassword())) {
            throw new BizException("用户或密码错误");
        }

        // Sa-Token登录
        StpUtil.login(dbUser.getUserId());


        // 查询当前用户角色和菜单 放入缓存
        List<Long> roleIds = userRoleMapper.selectObjs(
                        Wrappers.lambdaQuery(SysUserRole.class)
                                .select(SysUserRole::getRoleId)
                                .eq(SysUserRole::getUserId, StpUtil.getLoginIdAsLong()))
                .stream()
                .map(u -> ((Number) u).longValue())
                .collect(Collectors.toList());

        List<Long> menuIds = roleMenuMapper.selectObjs(
                        Wrappers.lambdaQuery(SysRoleMenu.class)
                                .select(SysRoleMenu::getMenuId)
                                .in(SysRoleMenu::getRoleId, roleIds))
                .stream()
                .map(u -> ((Number) u).longValue())
                .collect(Collectors.toList());

        StpUtil.getSession().set(CacheConstant.USER_ID, dbUser.getUserId());
        StpUtil.getSession().set(CacheConstant.USER_NAME, dbUser.getUsername());
        StpUtil.getSession().set(CacheConstant.USER_TEL, dbUser.getTel());
        StpUtil.getSession().set(CacheConstant.TENANT_ID, dbUser.getTenantId());
        StpUtil.getSession().set(CacheConstant.USER_DEPT_ID, dbUser.getDeptId());
        StpUtil.getSession().set(CacheConstant.USER_DETAIL, dbUser);
        StpUtil.getSession().set(CacheConstant.USER_MEMU_IDS, menuIds);
        StpUtil.getSession().set(CacheConstant.USER_ROLE_IDS, roleIds);


        // 构建返回对象，只返回 token
        UserLoginVo userVo = new UserLoginVo();
        userVo.setToken(StpUtil.getTokenValue());
        return userVo;
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(UserDto userDto) {
        SysUser sysUser = BeanUtil.copyProperties(userDto, SysUser.class);
        sysUser.setLockFlag(
                StrUtil.isBlank(userDto.getLockFlag()) ? UserStateEnum.NORMAL.getCode() : userDto.getLockFlag());
        sysUser.setCreateBy(userDto.getUsername());
        sysUser.setUpdateBy(userDto.getUsername());
        sysUser.setPassword(BCrypt.hashpw(userDto.getPassword()));
        baseMapper.insert(sysUser);

        // 插入用户部门信息
        if (Objects.nonNull(userDto.getDeptId())) {
            SysUserDept sysUserDept = new SysUserDept();
            sysUserDept.setUserId(sysUser.getUserId());
            sysUserDept.setDeptId(userDto.getDeptId());
            userDeptMapper.insert(sysUserDept);
        }


        // 如果角色为空，赋默认角色
        if (CollUtil.isEmpty(userDto.getRole())) {
            // 获取默认角色编码
//            String defaultRole = ParamResolver.getStr("USER_DEFAULT_ROLE");
            String defaultRole="USER_DEFAULT_ROLE";
            // 默认角色
            SysRole sysRole = roleService
                    .getOne(Wrappers.<SysRole>lambdaQuery().eq(SysRole::getCode, defaultRole));
            userDto.setRole(Collections.singletonList(sysRole.getRoleId()));
        }

        // 插入用户角色关系表
        userDto.getRole().stream().map(roleId -> {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(sysUser.getUserId());
            userRole.setRoleId(roleId);
            return userRole;
        }).forEach(userRoleMapper::insert);

        // 保存用户岗位信息
        Optional.ofNullable(userDto.getPost()).ifPresent(posts -> {
            posts.stream().map(postId -> {
                SysUserPost userPost = new SysUserPost();
                userPost.setUserId(sysUser.getUserId());
                userPost.setPostId(postId);
                return userPost;
            }).forEach(userPostMapper::insert);
        });

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserDto userDto) {
        SysUser user = new SysUser();
        BeanUtil.copyProperties(userDto, user);
        baseMapper.updateById(user);

        //删除部门
        userDeptMapper.delete(Wrappers.<SysUserDept>lambdaQuery().eq(SysUserDept::getUserId, user.getUserId()));
        //保存部门
        SysUserDept userDept = new SysUserDept();
        userDept.setUserId(user.getUserId());
        userDept.setDeptId(user.getDeptId());
        userDeptMapper.insert(userDept);


        //删除旧的角色ids
        userRoleMapper.delete(Wrappers.lambdaQuery(SysUserRole.class).eq(SysUserRole::getUserId, user.getUserId()));

        //保存角色id
        List<Long> roleIds = userDto.getRole();
        if (CollectionUtil.isEmpty(roleIds)) {
            return;
        }
        List<SysUserRole> userRoles = roleIds.stream().map(roleId -> {
            SysUserRole r = new SysUserRole();
            r.setUserId(user.getUserId());
            r.setRoleId(roleId);
            return r;
        }).collect(Collectors.toList());
        userRoleMapper.insert(userRoles);


        //删除岗位
        userPostMapper.delete(Wrappers.lambdaQuery(SysUserPost.class).eq(SysUserPost::getUserId, user.getUserId()));

        //保存岗位id
        List<Long> postIds = userDto.getPost();
        if (CollectionUtil.isEmpty(postIds)) {
            return;
        }

        List<SysUserPost> userPosts = postIds.stream().map(postId -> {
            SysUserPost r = new SysUserPost();
            r.setUserId(user.getUserId());
            r.setPostId(postId);
            return r;
        }).collect(Collectors.toList());
        userPostMapper.insert(userPosts);

    }

    @Override
    public IPage<UserVo> page(Page page, SysUser query) {

        // 构建用户查询条件
        LambdaQueryWrapper<SysUser> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StrUtil.isNotBlank(query.getUsername()), SysUser::getUsername, query.getUsername());
        wrapper.like(StrUtil.isNotBlank(query.getTel()), SysUser::getTel, query.getTel());
        wrapper.eq(ObjectUtil.isNotEmpty(query.getDeptId()), SysUser::getDeptId, query.getDeptId());
        wrapper.orderByDesc(SysUser::getCreateTime);

        //部门过滤：如果有部门条件，先查询符合条件的用户ID
        List<Long> queryUserIds = userDeptMapper.selectList(Wrappers.lambdaQuery(SysUserDept.class)
                        .eq(ObjectUtil.isNotEmpty(query.getDeptId()), SysUserDept::getDeptId, query.getDeptId()))
                .stream().map(u -> u.getUserId()).toList();

        if (CollUtil.isEmpty(queryUserIds)) {
            // 如果没有符合条件的用户，直接返回空结果
            page.setTotal(0);
            page.setRecords(new ArrayList<>());
            return page;
        }
        //分页查询
        wrapper.in(SysUser::getUserId, queryUserIds);
        IPage<SysUser> userPageResult = this.page(page, wrapper);


        //遍历学生获取结果转换
        List<UserVo> resultList=new ArrayList<>();
        IPage<UserVo> userVoPage = userPageResult.convert(user -> {
            UserVo vo = BeanUtil.copyProperties(user, UserVo.class);
            resultList.add(vo);

            //查询部门数据
            List<SysDept> deptList=getDeptListByUserId(user.getUserId());
            vo.setDeptList(deptList);
            //查询用户角色
            List<SysRole> roleList=getRoleListByUserId(user.getUserId());
            vo.setRoleList(roleList);

            //查询用户岗位
            List<SysPost> postList =getPostListByUserId(user.getUserId());
            vo.setPostList(postList);

            return vo;
        });

        return userVoPage;
    }


    @Override
    public UserLoginVo getLoginUserInfo() {
        // 获取当前登录用户ID
        Long userId = Long.parseLong(StpUtil.getLoginIdAsString());

        // 查询用户信息
        SysUser user = this.getById(userId);
        if (ObjectUtil.isEmpty(user)) {
            throw new BizException("用户不存在");
        }


        // 构建返回对象
        UserLoginVo vo = BeanUtil.copyProperties(user, UserLoginVo.class);
        vo.setRoleList(StpUtil.getRoleList());
        vo.setPermissions(StpUtil.getPermissionList());
        vo.setTenantName(""); // 租户名称，如果需要可以从租户服务查询

        return vo;
    }


    /**
     * 获取用户详情（包含角色、岗位、部门列表）
     */
    @Override
    public UserVo getUserDetailById(Long id) {
        // 查询用户信息
        SysUser user = this.getById(id);
        if (ObjectUtil.isEmpty(user)) {
            throw new BizException("用户不存在");
        }

        UserVo vo = BeanUtil.copyProperties(user, UserVo.class);

        //查询部门列表
        List<SysDept> deptList=getDeptListByUserId(user.getUserId());
        vo.setDeptList(deptList);
        //查询角色列表
        List<SysRole> roleList=getRoleListByUserId(user.getUserId());
        vo.setRoleList(roleList);
        //查询岗位列表
        List<SysPost> postList=getPostListByUserId(user.getUserId());
        vo.setPostList(postList);

        return vo;
    }

    @Override
    public UserVo getUserDetail(UserQueryDto userQueryDto) {
        SysUser sysUser=BeanUtil.copyProperties(userQueryDto, SysUser.class);

        // 查询用户信息
        SysUser user = this.getOne(Wrappers.query(sysUser));
        if (ObjectUtil.isEmpty(user)) {
            throw new BizException("用户不存在");
        }

        UserVo vo = BeanUtil.copyProperties(user, UserVo.class);

        //查询部门列表
        List<SysDept> deptList=getDeptListByUserId(user.getUserId());
        vo.setDeptList(deptList);
        //查询角色列表
        List<SysRole> roleList=getRoleListByUserId(user.getUserId());
        vo.setRoleList(roleList);
        //查询岗位列表
        List<SysPost> postList=getPostListByUserId(user.getUserId());
        vo.setPostList(postList);

        return vo;
    }





    //查询用户部门
    private List<SysDept> getDeptListByUserId(Long userId){
        List<Long> deptIdList = userDeptMapper.selectList(
                Wrappers.lambdaQuery(SysUserDept.class).eq(SysUserDept::getUserId,userId))
                .stream().map(SysUserDept::getDeptId).toList();

        List<SysDept> deptList=CollUtil.newArrayList();
        if(CollUtil.isNotEmpty(deptIdList)){
            deptList=deptService.list(Wrappers.lambdaQuery(SysDept.class).in(SysDept::getDeptId, deptIdList));
        }
        return deptList;
    }

    private List<SysRole> getRoleListByUserId(Long userId){
        //查询用户角色
        List<Long> roleIdList = userRoleMapper.selectObjs(Wrappers.lambdaQuery(SysUserRole.class)
                .select(SysUserRole::getRoleId)
                .eq(SysUserRole::getUserId,userId)).stream().map(r -> (Long) r).toList();

        List<SysRole> roleList=CollUtil.newArrayList();
        if(CollUtil.isNotEmpty(roleIdList)){
            roleList = roleService.list(Wrappers.lambdaQuery(SysRole.class).in(SysRole::getRoleId, roleIdList));
        }
        return roleList;
    }

    private List<SysPost> getPostListByUserId(Long userId){
        //查询用户岗位
        List<Long> postIdList = userPostMapper.selectList(
                        Wrappers.lambdaQuery(SysUserPost.class).select(SysUserPost::getPostId).eq(SysUserPost::getUserId, userId))
                .stream().map(p -> p.getPostId()).toList();

        List<SysPost> postList=CollUtil.newArrayList();
        if(CollUtil.isNotEmpty(postIdList)){
            postList = postService.list(Wrappers.lambdaQuery(SysPost.class).in(SysPost::getPostId, postIdList));

        }
        return postList;
    }
}




