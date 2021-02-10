package com.fadedos.springbootjspshiro.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:TODO
 * @author: pengcheng
 * @date: 2020/12/24
 */
@Configuration
public class ApplicationContextUtils  implements ApplicationContextAware {

    private static ApplicationContext appContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (appContext == null) {
            appContext = applicationContext;
        }
    }
    /**
     * 通过name获取 Bean.
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return appContext.getBean(name);

    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static < T > T getBean(Class< T > clazz) {
        return appContext.getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static < T > T getBean(String name, Class< T > clazz) {
        return appContext.getBean(name, clazz);
    }

}


