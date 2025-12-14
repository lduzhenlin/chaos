
package com.qishanor.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qishanor.common.util.R;
import com.qishanor.system.model.SysDict;
import com.qishanor.system.model.dto.DictDto;

/**
 * 字典表
 */
public interface SysDictService extends IService<SysDict> {


	/**
	 * 更新字典
	 */
	R updateDict(DictDto dictDto);

	/**
	 * 根据ID 删除字典
	 */
	R removeDictByIds(Long[] ids);

	/**
	 * 同步缓存 （清空缓存）
	 */
	R syncDictCache();

}
