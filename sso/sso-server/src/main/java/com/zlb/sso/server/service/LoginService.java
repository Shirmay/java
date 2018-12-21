package com.zlb.sso.server.service;

import com.zlb.sso.server.bean.UserBean;
import com.zlb.sso.server.dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginDao loginDao;

    public UserBean getUserByLoginName(String loginName){
        return loginDao.getUserByLoginName(loginName);
    }
}
