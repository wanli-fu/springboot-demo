package com.fadedos.springbootredis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description:TODO
 * @author: pengcheng
 * @date: 2020/12/24
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private String salt;
}