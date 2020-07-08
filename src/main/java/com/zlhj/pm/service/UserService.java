package com.zlhj.pm.service;

import com.zlhj.pm.model.User;

/**
 * 用户业务接口
 * @author tzm
 * @version 1.0
 * @since 2020/5/6 18:36
 */
public interface UserService {
    /**
     *
     * @param user 用户对象
     * @return 成功则返回"success"，失败则返回错误信息
     */
    String addUser(User user);
}
