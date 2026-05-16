package org.perswsj.controller;

import lombok.extern.slf4j.Slf4j;
import org.perswsj.dto.LoginDto;
import org.perswsj.model.LoginInfo;
import org.perswsj.model.Result;
import org.perswsj.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    /**
     * 登录接口
     * @return 登录结果
     */
    @PostMapping
    public Result login(@RequestBody LoginInfo loginInfo) {
        log.info("登录: {}", loginInfo);
        // 登录逻辑
        LoginDto loginDto = loginService.login(loginInfo);
        if (loginDto == null) {
            return Result.error("登录失败,用户名或密码错误");
        }
//        log.info("登录成功: {}", loginDto);
        return Result.success(loginDto);
    }

}
