package com.fadedos.springbootjspshiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Description:TODO
 * @author: pengcheng
 * @date: 2020/12/25
 */
@Data
@Accessors(chain =true)
@AllArgsConstructor
@NoArgsConstructor
public class Perms {
    private int id;
    private String name;
    private String url;
}
