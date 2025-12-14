package com.qishanor.system.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qishanor.system.model.SysPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 岗位信息表
 *
 */
@Mapper
public interface SysPostMapper extends BaseMapper<SysPost> {

    /**
     * 通过用户ID和租户ID查询岗位信息
     *
     * @param userId   用户ID
     * @param tenantId 租户ID
     * @return 岗位信息列表
     */
//    @InterceptorIgnore(tenantLine = "true")
//    List<SysPost> listPostsByUserId(@Param("userId") Long userId, @Param("tenantId") Long tenantId);

}
