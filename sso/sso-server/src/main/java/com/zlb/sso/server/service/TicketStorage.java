package com.zlb.sso.server.service;

import com.zlb.sso.server.bean.UserBean;
import com.zlb.sso.server.id.IdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TicketStorage {

    private static Logger logger = LoggerFactory.getLogger(TicketStorage.class);

    private static Map<String, UserBean> storage = new ConcurrentHashMap<>();

    public static final String KEY = "_ticket";

    @Autowired
    private IdGenerator idGenerator;

    public String put(UserBean user){
        String ticket = idGenerator.getNextId();
        storage.put(ticket, user);
        return ticket;
    };

    public UserBean remove(String ticket){
        UserBean user = storage.get(ticket);
        if (user == null){
            logger.warn("通过ticket:{},获取用户为空");
            return null;
        }
        return storage.remove(ticket);
    }

    public UserBean get(String ticket){
        return storage.get(ticket);
    }
}
