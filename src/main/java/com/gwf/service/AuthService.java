package com.gwf.service;

import com.gwf.pojo.User;

/**
 * Created by gaowenfeng on 2017/8/9.
 */
public interface AuthService {
    User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
