package com.qishanor.system.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @description:
 * @author: 周振林
 * @date: 2022-04-17
 * 
 * @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
 **/

@Data
@Accessors(chain = true)
public class UserDto {

    @Schema(description = "主键id")
    private Long userId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "手机号")
    private String tel;

    @Schema(description = "头像地址")
    private String avatar;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "真实姓名")
    private String name;
    @Schema(description = "是否是管理员")
    private Integer isSuper;
    @Schema(description = "是否启用")
    private String lockFlag;
    private Long deptId;
//    private List<Long> roleIds;
    private List<Long> role;//用户保存修改
    private List<Long> post;//用户保存修改

}
