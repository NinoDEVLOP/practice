package com.practice.demo.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Classname: CompareUtil
 * @Description:
 * @Author: chingyuean
 * @Date: 2020/11/11 7:17 下午
 */
public class CompareUtil {

    public static boolean equals(Object source, Object target){
        if (source.getClass()!=target.getClass()){
            return false;
        }
        Class<?> aClass = source.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Class<?> type = declaredField.getType();
            try {
                String sourceValue = declaredField.get(source).toString();
                String targetValue = declaredField.get(target).toString();
                if (!sourceValue.equals(targetValue)){
                    return false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}
