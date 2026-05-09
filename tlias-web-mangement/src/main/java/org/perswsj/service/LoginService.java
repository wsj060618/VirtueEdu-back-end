package org.perswsj.service;

import org.perswsj.dto.LoginDto;
import org.perswsj.model.LoginInfo;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    /**
     * 登录
     * @param loginInfo 登录信息
     * @return LoginDto 登录凭证信息
     */
    LoginDto login(LoginInfo loginInfo);
}
