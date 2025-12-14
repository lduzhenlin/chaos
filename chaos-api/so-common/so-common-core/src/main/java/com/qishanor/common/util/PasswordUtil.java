package com.qishanor.common.util;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;

/**
 * @description: 本项目的前端密码加密和解密
 * @author: 周振林
 * @date: 2022-04-23
 * @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
 **/

public class PasswordUtil {


    //如需对password 进行解密 可以执行下面代码 块大小默认48byte
    // AES aes = new AES(Mode.CFB, Padding.NoPadding,"chaosqishanorcom".getBytes(),"chaosqishanorcom".getBytes());
    // String test=aes.encryptBase64("123456").toString();//加密
    // password=aes.decryptStr(test);//解密

    /**
     * 本项目前端密码加密方式
     *
     * @param password 要加密的明文密码
     * @return 加密后的字符串
     */
    public static String  encryptFrontPassword(String password){
        AES aes = new AES(Mode.CFB, Padding.NoPadding,"www.qishanor.com".getBytes(),"www.qishanor.com".getBytes());
        //加密
        String result=aes.encryptBase64(password);
        return result;
    }

    /**
     * 本项目前端密码解密方式
     * @param password 加密后的密文密码
     * @return 解密后的字符串
     */
    public static String decryptFrontPassword(String password){
        AES aes = new AES(Mode.CFB, Padding.NoPadding,"qishanorcomcomcom".getBytes(),"qishanorcomcomcom".getBytes());
        //解密
        String result=aes.decryptStr(password);
        return result;
    }
}
