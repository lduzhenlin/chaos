package com.qishanor.system.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @description:
 * @author: 周振林
 * @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
 **/

@Data
@Accessors(chain = true)
public class UserPersonalEditDto {

    @NotBlank(message = "用户id不能为空")
    private String userId;
    @NotBlank(message = "手机号不能为空")
    private String tel;
    @Email
    @NotBlank(message = "邮箱不能为空")
    private String email;

    private String name;

    private String nickname;

    private String avatar;

}
