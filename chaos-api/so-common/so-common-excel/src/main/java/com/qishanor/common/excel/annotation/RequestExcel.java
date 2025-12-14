package com.qishanor.common.excel.annotation;

import com.qishanor.common.excel.handler.DefaultAnalysisEventListener;
import com.qishanor.common.excel.handler.ListAnalysisEventListener;

import java.lang.annotation.*;

/**
 * 导入excel
 *
 */
@Documented
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestExcel {

	/**
	 * 前端上传字段名称 file
	 */
	String fileName() default "file";

	/**
	 * 读取的监听器类
	 * @return readListener
	 */
	Class<? extends ListAnalysisEventListener<?>> readListener() default DefaultAnalysisEventListener.class;

	/**
	 * 是否跳过空行
	 * @return 默认跳过
	 */
	boolean ignoreEmptyRow() default false;

	/**
	 * 指定读取的标题行
	 * @return
	 */
	int headRowNumber() default 1;

}
