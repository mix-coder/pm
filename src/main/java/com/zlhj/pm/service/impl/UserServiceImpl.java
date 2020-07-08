package com.zlhj.pm.service.impl;

import com.zlhj.pm.model.User;
import com.zlhj.pm.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author tzm
 * @version 1.0
 * @since 2020/5/6 18:37
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String addUser(User user) {
        // 直接编写业务逻辑
        return "success";
    }
}
