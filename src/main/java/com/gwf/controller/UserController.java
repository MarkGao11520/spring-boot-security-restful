package com.gwf.controller;

import com.gwf.dao.UserRepository;
import com.gwf.pojo.User;
import com.gwf.service.JwtUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/8/9.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostAuthorize("returnObject.username == principal.username or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public User getUserByUsername(@RequestParam(value="username") String username) {
        User result = userRepository.getMap().get(username);
        return result==null?new User():result;
    }

}
