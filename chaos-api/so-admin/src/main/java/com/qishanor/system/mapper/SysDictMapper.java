package com.qishanor.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qishanor.system.model.SysDict;
import com.qishanor.system.model.SysDictItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.ResultHandler;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 字典表 Mapper 接口
 */
@Mapper
public interface SysDictMapper extends BaseMapper<SysDict> {


}
