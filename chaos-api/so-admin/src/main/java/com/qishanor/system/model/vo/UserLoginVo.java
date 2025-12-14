package com.qishanor.system.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 登录返回对象
 * @author: 周振林
 * @date: 2022-04-22
 * 
 * @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
 **/

@Data
@Accessors(chain = true)
public class UserLoginVo {

    private Long userId;
    private String username;
    private String tel;
    private String email;
    private String avatar;
    private Integer isSuper;
    private Long deptId;
    private Long roleId;
    private Long postId;

    
    // 角色列表
    private List<String> roleList = new ArrayList<>();
    // 权限列表
    private List<String> permissions = new ArrayList<>();

    // 租户信息
    private Long tenantId;
    private String tenantName;

    private String token;


}
