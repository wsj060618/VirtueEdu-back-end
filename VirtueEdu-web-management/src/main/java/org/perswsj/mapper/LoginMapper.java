package org.perswsj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.perswsj.model.LoginInfo;

@Mapper
public interface LoginMapper {
    LoginInfo selectByUserNameAndPassword(LoginInfo loginInfo);
}
