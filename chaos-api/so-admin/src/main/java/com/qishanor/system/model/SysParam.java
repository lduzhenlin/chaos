package com.qishanor.system.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


/**
 * 公共参数配置表
 *
 * @author Joe
 * @date 2022-06-06 20:18:04
 */

@Data
@TableName("sys_param")
@NoArgsConstructor
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
public class SysParam  {


    @TableId(type= IdType.AUTO)
    private Integer id;
    /**
     * 名称
     */
    private String name;

    /**
     * 键
     */
    @TableField("`key`")
    private String key;

    /**
     * 值
     */
    private String value;

    /**
     * 系统内置
     */
    private String isSystem;

    /**
     * 状态
     */
    private String status;


}
