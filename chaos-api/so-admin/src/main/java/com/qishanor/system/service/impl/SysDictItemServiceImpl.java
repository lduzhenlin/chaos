package com.qishanor.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qishanor.common.constant.CacheConstant;
import com.qishanor.common.enums.DictTypeEnum;
import com.qishanor.common.util.R;
import com.qishanor.system.mapper.SysDictItemMapper;
import com.qishanor.system.model.SysDict;
import com.qishanor.system.model.SysDictItem;
import com.qishanor.system.service.SysDictItemService;
import com.qishanor.system.service.SysDictService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * 字典项
 *
 * @author joe
 * @date 2019/03/19
 */
@Service
@AllArgsConstructor
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements SysDictItemService {

	private final SysDictService dictService;


	/**
	 * 更新字典项
	 */
	@Override
	@CacheEvict(value = CacheConstant.DICT_DETAIL, key = "#item.dictType")
	public R updateDictItem(SysDictItem item) {
		// 查询字典
		SysDict dict = dictService.getById(item.getDictId());
		// 系统内置
		if (DictTypeEnum.SYSTEM.getType().equals(dict.getSystemFlag())) {
			return R.failed("系统内置属性不能修改");
		}
		return R.ok(this.updateById(item));
	}

	/**
	 * 删除字典项
	 */
	@Override
	@CacheEvict(value = CacheConstant.DICT_DETAIL, allEntries = true)
	public R removeDictItem(Long id) {
		// 根据ID查询字典ID
		SysDictItem dictItem = this.getById(id);
		SysDict dict = dictService.getById(dictItem.getDictId());
		// 系统内置
		if (DictTypeEnum.SYSTEM.getType().equals(dict.getSystemFlag())) {
			return R.failed("系统内置属性不能删除");
		}
		return R.ok(this.removeById(id));
	}

}
