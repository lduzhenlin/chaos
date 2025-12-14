package com.qishanor.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qishanor.common.constant.CacheConstant;
import com.qishanor.common.enums.DictTypeEnum;
import com.qishanor.common.util.R;
import com.qishanor.system.mapper.SysDictItemMapper;
import com.qishanor.system.mapper.SysDictMapper;
import com.qishanor.system.model.SysDict;
import com.qishanor.system.model.SysDictItem;
import com.qishanor.system.model.dto.DictDto;
import com.qishanor.system.service.SysDictService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 字典表
 *
 * @author joe
 * @date 2019/03/19
 */
@Service
@AllArgsConstructor
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

	private final SysDictItemMapper dictItemMapper;


	/**
	 * 更新字典
	 */
	@Override
	@CacheEvict(value = CacheConstant.DICT_DETAIL, key = "#dict.dictType")
	public R updateDict(DictDto dictDto) {
		SysDict dict= BeanUtil.copyProperties(dictDto, SysDict.class);

		SysDict dbDict = this.getById(dict.getDictId());
		// 系统内置
		if (DictTypeEnum.SYSTEM.getType().equals(dbDict.getSystemFlag())) {
			return R.failed("系统内置属性不能修改");
		}
		this.updateById(dict);
		return R.ok(dict);

	}

	/**
	 * 根据ID 删除字典
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(value = CacheConstant.DICT_DETAIL, allEntries = true)
	public R removeDictByIds(Long[] ids) {
		if(CollUtil.isEmpty(Arrays.asList(ids))){
			return R.failed("id不能为空");
		}

		List<Long> dictIdList = baseMapper.selectByIds(CollUtil.toList(ids))
			.stream()
			.filter(sysDict -> !sysDict.getSystemFlag().equals(DictTypeEnum.SYSTEM.getType()))// 系统内置类型不删除
			.map(SysDict::getDictId)
                .toList();

		baseMapper.deleteByIds(dictIdList);
		dictItemMapper.delete(Wrappers.<SysDictItem>lambdaQuery().in(SysDictItem::getDictId, dictIdList));
		return R.ok();
	}



	/**
	 * 同步缓存 （清空缓存）
	 * @return R
	 */
	@Override
	@CacheEvict(value = CacheConstant.DICT_DETAIL, allEntries = true)
	public R syncDictCache() {
		return R.ok();
	}

}
