package com.gwf.service;

import com.gwf.dao.UserRepository;
import com.gwf.pojo.JwtUser;
import com.gwf.pojo.Role;
import com.gwf.pojo.User;
import com.gwf.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by gaowenfeng on 2017/8/9.
 */
@Service
public class AuthServiceImpl implements AuthService{
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserDetailsService userDetailsService,
                           UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public User register(User userToAdd) {
        final String username = userToAdd.getUsername();
        if(userRepository.getMap().get(username)!=null) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(encoder.encode(rawPassword));
        userToAdd.setRoles(Arrays.asList(new Role("ROLE_USER")));
        userRepository.getMap().put(username,userToAdd);
        return userToAdd;
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = JwtUtil.setClaim(username);
        return token;
    }

    @Override
    public String refresh(String oldToken) {
        return null;
    }
}
