package com.think.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
/**
 * Spring 工具类,需要在Spring容器中注册
 * @author hg
 * @date 2021-08-06日 10:32
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据名称获取具体的BEAN
     * @param name
     * @param <T>
     * @return
     * @throws BeansException
     */
    public static <T> T getBean(String name) throws BeansException {
        if (applicationContext == null){
            return null;
        }
        return (T)applicationContext.getBean(name);
    }

    /**
     * 根据Class获取BEAN
     * @param cls
     * @param <T>
     * @return
     * @throws BeansException
     */
    public static <T> T getBean(Class cls) throws BeansException {
        if (applicationContext == null){
            return null;
        }
        return (T)applicationContext.getBean(cls);
    }
}
