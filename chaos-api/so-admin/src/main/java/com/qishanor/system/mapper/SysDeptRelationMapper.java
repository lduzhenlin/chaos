package com.qishanor.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qishanor.system.model.SysDeptRelation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @description: 部门关系表 Mapper接口
 * @author: 周振林
 * @date: 2022-04-16
 * 
 * @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
 **/

@Mapper
public interface SysDeptRelationMapper extends BaseMapper<SysDeptRelation> {
    /**
     * 删除部门 > 删除所有关联此部门子节点的闭包关系
     * @param id 部门ID
     */
    void deleteDeptRelationsByDeptId(Long id);



}
