package com.qishanor.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qishanor.common.util.R;
import com.qishanor.system.model.SysPost;
import com.qishanor.system.model.vo.PostExcelVO;
import org.springframework.validation.BindingResult;
import java.util.List;

/**
 * 岗位信息表
 *
 * @author fxz
 * @date 2022-03-26 12:50:43
 */
public interface SysPostService extends IService<SysPost> {

	/**
	 * 导出excel 表格
	 * @return
	 */
	List<PostExcelVO> listPost(SysPost post, Long[] ids);

	/**
	 * 导入岗位
	 * @param excelVOList 岗位列表
	 * @param bindingResult 错误信息列表
	 * @return ok fail
	 */
	R importPost(List<PostExcelVO> excelVOList, BindingResult bindingResult);

}
