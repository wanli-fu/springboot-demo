package com.fadedos.springbootjspshiro.shiro.realms;

import com.fadedos.springbootjspshiro.entity.Perms;
import com.fadedos.springbootjspshiro.entity.User;
import com.fadedos.springbootjspshiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Description:TODO
 * @author: pengcheng
 * @date: 2020/12/24
 */
public class CustomerRealm extends AuthorizingRealm {
    /**
     * 授权
     */
    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        System.out.println("授权开始");
        //获取身份信息
        String primaryPrincipal = (String) principals.getPrimaryPrincipal();

        //根据主身份信息 查询数据库 获取角色和权限信息
        User user = userService.findRolesByUsername(primaryPrincipal);
        //授权角色信息
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(role -> {
                simpleAuthorizationInfo.addRole(role.getName());

                //授权权限信息
                List<Perms> permsList = userService.findPermsByRoleId(role.getId());
                if (!CollectionUtils.isEmpty(permsList)) {
                    permsList.forEach(perm ->{
                        simpleAuthorizationInfo.addStringPermission(perm.getName());
                    });
                }
            });
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     *rrr
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("=================");

        //根据身份信息
        String principal = (String) token.getPrincipal();

        User user = userService.findByUsername(principal);
        if (!ObjectUtils.isEmpty(user)) {
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(),
                    ByteSource.Util.bytes(user.getSalt()), getName());
        }
        return null;
    }
}
