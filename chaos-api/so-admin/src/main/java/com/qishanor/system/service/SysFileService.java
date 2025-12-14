package com.qishanor.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qishanor.common.util.R;
import com.qishanor.system.model.SysFile;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;



/**
 * 文件管理表表 服务类
 *
 * @author Joe
 * @date 2022-06-22 15:56:09
 */
public interface SysFileService extends IService<SysFile> {

//    R uploadFile(MultipartFile file);
//
//    void getFile(String bucket, String fileName, HttpServletResponse response);
}
