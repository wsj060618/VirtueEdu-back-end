package org.perswsj.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.perswsj.utils.JwtUtil;
import org.perswsj.dto.LoginDto;
import org.perswsj.mapper.LoginMapper;
import org.perswsj.model.LoginInfo;
import org.perswsj.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Override
    public LoginDto login(LoginInfo loginInfo) {
        // 登录逻辑
        LoginInfo login = loginMapper.selectByUserNameAndPassword(loginInfo);
//        log.info("登录信息: {}", login);
        if (login == null) {
            log.error("登录失败，用户名或密码错误");
            return null;
        }
        // 生成JWT令牌
        Map<String, Object> map = new HashMap<>();
        map.put("id", login.getId());
        map.put("username", login.getUsername());
        map.put("name", login.getName());
        String token = JwtUtil.generateToken(map);
        login.setToken(token);
        return new LoginDto(login.getId(), login.getUsername(), login.getName(), token);
    }
}
