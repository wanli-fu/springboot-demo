package com.fadedos.springbootjspshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:TODO
 * @author: pengcheng
 * @date: 2020/12/25
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @RequestMapping("/save")
//    @RequiresRoles(value = {"admin","user"}) //用来判断角色 同时具有 admin user
    @RequiresPermissions("user:update:01")
    public String save(){

        //代码形式 授权

        //获取主体对象
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("admin")) {
            System.out.println("保存订单");
        } else {
            System.out.println("无权访问");
        }

        //基于权限字符创授权
        return "redirect:/index.jsp";
    }
}
