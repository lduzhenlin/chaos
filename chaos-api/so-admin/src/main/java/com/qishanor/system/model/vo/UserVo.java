package com.qishanor.system.model.vo;

import com.qishanor.system.model.SysDept;
import com.qishanor.system.model.SysPost;
import com.qishanor.system.model.SysRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户VO，匹配XML resultMap结构
 */
@Data
@Accessors(chain = true)
public class UserVo {

    private Long userId;
    private String username;
    private String tel;
    private String avatar;
    private String nickname;
    private String name;
    private String email;
    private String lockFlag;

    private LocalDateTime createTime;

    // 部门列表
    private List<SysDept> deptList = new ArrayList<>();
    // 角色列表
    private List<SysRole> roleList = new ArrayList<>();
    // 岗位列表
    private List<SysPost> postList = new ArrayList<>();
}

