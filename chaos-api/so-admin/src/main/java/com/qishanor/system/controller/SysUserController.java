package com.qishanor.system.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qishanor.common.util.DictVO;
import com.qishanor.common.util.R;
import com.qishanor.system.model.SysUser;
import com.qishanor.system.model.dto.UserDto;
import com.qishanor.system.model.dto.UserPersonalEditDto;
import com.qishanor.system.model.dto.UserQueryDto;
import com.qishanor.system.model.vo.UserVo;
import com.qishanor.system.service.SysRoleService;
import com.qishanor.system.service.SysUserRoleService;
import com.qishanor.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: 周振林
 * @date: 2022-04-08
 * @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
 **/

@RestController
@RequestMapping("/admin/user")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 获取用户字典
     */
    @GetMapping("/user_dict")
    public R getList() {
        List<DictVO> results= userService.list(Wrappers.emptyWrapper())
                .stream().map(u->new DictVO<Integer>(u.getUsername(),u.getUserId().toString())).collect(Collectors.toList());
        return R.ok(results);
    }

    /**
     * 获取当前登录用户信息
     */
    @GetMapping("/info")
    @SaCheckLogin
    public R getLoginUserInfo(){
        return R.ok(userService.getLoginUserInfo());
    }

    @GetMapping("/page")
    @SaCheckPermission("sys_user_view")
    public R page(Page page, SysUser sysUser){
        IPage<UserVo> result = userService.page(page, sysUser);
        return R.ok(result);
    }

    @GetMapping("/detail/{id}")
    public R getById(@PathVariable Long id){
        // 使用 UserVO 返回包含角色、岗位、部门列表的完整用户信息
        UserVo vo = userService.getUserDetailById(id);
        return R.ok(vo);
    }

    @GetMapping("/detail")
    public Object getDetail(UserQueryDto userQueryDto){
        UserVo vo = userService.getUserDetail(userQueryDto);
        return R.ok(vo);

    }

    @PostMapping
    @SaCheckPermission("sys_user_add")
    public R save(@RequestBody UserDto userDto){
        userService.saveUser(userDto);
        return R.ok();
    }

    @PutMapping
    @SaCheckPermission("sys_user_edit")
    public R update(@RequestBody UserDto userDto){
        userService.updateUser(userDto);
        return R.ok();
    }

    /**
     * 个人资料信息更新
     */
    @PutMapping("/personal/edit")
    public Object edit(@RequestBody UserPersonalEditDto userDto){
        SysUser dbUser = userService.getById(userDto.getUserId());
        if(ObjectUtil.isEmpty(dbUser)){
            return R.failed("用户不存在");
        }

        SysUser user= BeanUtil.copyProperties(userDto, SysUser.class);
        userService.updateById(user);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @SaCheckPermission("sys_user_del")
    public R removeById(@PathVariable Long id){
        userService.removeById(id);
        return  R.ok();
    }

//    @PutMapping("/reset/{id}")
//    public R resetPassword(@PathVariable Long id){
//        SysUser sysUser=sysUserService.getById(id);
//        if(ObjectUtil.isEmpty(sysUser)){
//            return  R.failed("用户不存在");
//        }
//
//        //重置为默认密码：123456
//        String frontPassword= PasswordUtil.encryptFrontPassword("123456");
//        String newPassword = SaSecureUtil.md5BySalt(frontPassword,"www.qishanor.com");
//        sysUser.setPassword(newPassword);
//        sysUserService.updateById(sysUser);
//        return R.ok();
//    }

}
