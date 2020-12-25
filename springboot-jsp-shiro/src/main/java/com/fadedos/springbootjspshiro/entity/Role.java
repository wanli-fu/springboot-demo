package com.fadedos.springbootjspshiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description:TODO
 * @author: pengcheng
 * @date: 2020/12/25
 */
@Data
@Accessors(chain =true)
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private int id;
    private String name;

    //定义权限集合
    private List<Perms> permsList;
}
