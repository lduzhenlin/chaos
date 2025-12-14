package com.qishanor.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qishanor.system.model.SysDept;
import com.qishanor.system.model.SysDeptRelation;

/**
 * @description: 部门关系表 服务类
 * @author: 周振林
 * @date: 2022-04-16
 * @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
 **/

public interface SysDeptRelationService extends IService<SysDeptRelation> {

    void insertDeptRelation(SysDept sysDept);

    void updateDeptRelation(SysDept sysDept);

    void deleteDeptRealtion(Long id);

}
