package com.gwf.service;

import com.gwf.dao.UserRepository;
import com.gwf.pojo.JwtUserFactory;
import com.gwf.pojo.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/2/5.
 */
@Data
public class JwtUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.getMap().get(s);
        if (user == null)
            throw new UsernameNotFoundException("用户名不存在");
        return JwtUserFactory.create(user);
    }
}
