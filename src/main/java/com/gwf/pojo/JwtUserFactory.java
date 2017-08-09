package com.gwf.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by gaowenfeng on 2017/8/9.
 */
public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user){
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                map2GrantedAuthorities(user.getRoles())
        );
    }

    private static List<GrantedAuthority> map2GrantedAuthorities(List<Role> authorities){
        return authorities.stream()
                .map(e -> role2SimpleGrantedAuthority(e))
                .collect(Collectors.toList());
    }

    private static SimpleGrantedAuthority role2SimpleGrantedAuthority(Role role){
        return new SimpleGrantedAuthority(role.getName());
    }
}
