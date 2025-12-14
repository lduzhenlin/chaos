package com.qishanor.system.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.qishanor.common.util.R;
import com.qishanor.system.model.vo.UserLoginVo;
import com.qishanor.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 用户登录管理
 * @author: 周振林
 * @date: 2022-04-17
 * @Copyright: 博客：http://www.zhouzhenlin.com - 沉淀、分享、成长、让自己和他人都有所收获
 **/

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class LoginController {

    private final SysUserService sysUserService;


//    @Autowired
//    private CaptchaService captchaService;


//    @PostMapping("/code")
//    public R getCode(@RequestBody CaptchaVO captchaVO) {
//        return R.ok(captchaService.get(captchaVO));
//    }
//    @PostMapping("/check")
//    public R checkCode(@RequestBody CaptchaVO captchaVO) {
//        return R.ok(captchaService.check(captchaVO));
//    }

//    @PostMapping("/verify")
//    public ResponseModel verify(@RequestBody CaptchaVO captchaVO) {
//        return captchaService.verification(captchaVO);
//    }


    @SaIgnore
    @PostMapping("/login")
    public Object login(@RequestParam String username, @RequestParam String password){
            // 登录
            UserLoginVo userVo =sysUserService.login(username, password);

            return R.ok(userVo);
    }

    @DeleteMapping("/logout")
    public Object logout(){
        StpUtil.logout();
        return R.ok();
    }



}
