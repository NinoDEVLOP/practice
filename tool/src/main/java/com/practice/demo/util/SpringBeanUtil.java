package com.zhigui.bif.util;

import org.springframework.context.ApplicationContext;

public class SpringBeanUtil {

    private static ApplicationContext applicationContext;

    public static void setContext(ApplicationContext applicationContext) {
        SpringBeanUtil.applicationContext = applicationContext;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }
}

