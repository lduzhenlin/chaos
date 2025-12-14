package com.qishanor.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qishanor.system.mapper.SysDeptRelationMapper;
import com.qishanor.system.model.SysDept;
import com.qishanor.system.model.SysDeptRelation;
import com.qishanor.system.service.SysDeptRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 部门关系表 服务实现类
 * @author: 周振林
 * @date: 2022-04-16
 * @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
 **/

@Service
public class SysDeptRelationServiceImpl extends ServiceImpl<SysDeptRelationMapper, SysDeptRelation> implements SysDeptRelationService {


    /**
     * 维护部门关系
     * @param sysDept 部门
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertDeptRelation(SysDept sysDept) {
        // 查询该部门上级所有的部门并增加关联信息
        List<SysDeptRelation> relationList = baseMapper.selectList(
                Wrappers.<SysDeptRelation>query().lambda().eq(SysDeptRelation::getDescendant, sysDept.getParentId()))
                .stream().map(relation -> {
                    relation.setDescendant(sysDept.getDeptId());
                    return relation;
                }).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(relationList)) {
            relationList.forEach(ele -> baseMapper.insert(ele));
        }

        // 自己也要维护到关系表中
        SysDeptRelation own = new SysDeptRelation();
        own.setDescendant(sysDept.getDeptId());
        own.setAncestor(sysDept.getDeptId());
        baseMapper.insert(own);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDeptRelation(SysDept sysDept) {
        this.deleteDeptRealtion(sysDept.getDeptId());
        this.insertDeptRelation(sysDept);
    }

    /**
     * 通过ID删除部门关系
     */
    @Override
    public void deleteDeptRealtion(Long id) {
        baseMapper.deleteDeptRelationsByDeptId(id);
    }


}
