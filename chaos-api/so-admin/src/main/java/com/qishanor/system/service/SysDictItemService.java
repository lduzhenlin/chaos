package com.qishanor.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qishanor.common.util.R;
import com.qishanor.system.model.SysDictItem;

/**
 * 字典项
 */
public interface SysDictItemService extends IService<SysDictItem> {

	/**
	 * 删除字典项
	 * @param id 字典项ID
	 * @return
	 */
	R removeDictItem(Long id);

	/**
	 * 更新字典项
	 * @param item 字典项
	 * @return
	 */
	R updateDictItem(SysDictItem item);

}
