package com.gwf.dao;

import com.gwf.pojo.User;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/8/9.
 */
@Repository
@Data
public class UserRepository {
    private Map<String,User> map;

    public UserRepository() {
        map = new HashMap<>();
    }
}
