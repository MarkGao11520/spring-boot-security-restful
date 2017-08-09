package com.gwf.pojo;

import lombok.Data;

/**
 * Created by gaowenfeng on 2017/8/9.
 */
@Data
public class Role {
    private String name;

    public Role(String name) {
        this.name = name;
    }
}
