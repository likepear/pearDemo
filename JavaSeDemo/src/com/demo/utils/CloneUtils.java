package com.demo.utils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 对象拷贝工具类
 */
public class CloneUtils {

    /**
     * 返回目标对象的拷贝
     * @param t
     * @param <T>
     * @return
     */
    public static <T extends Serializable,Cloneable> T getClone(T t){

        T proxyObj = null;

        T proxy = getProxy(t);

        return proxy;
    }

    /**
     * 返回目标类的动态代理类
     * @param t
     * @param <T>
     * @param <Cloneable>
     * @return
     */
    private static <T extends Serializable,Cloneable> T getProxy(T t){

        if(t!=null){

            Object proxyObj = Proxy.newProxyInstance(t.getClass().getClassLoader(), t.getClass().getInterfaces(), (proxy, method, args) -> {
                Method[] methods = proxy.getClass().getMethods();
                Object invoke = method.invoke(t, args);
                return invoke;
            });

            return (T) proxyObj;

        }

        return null;

    }

    /**
     * 获取对象的所有方法的拷贝
     * @param t
     * @param <T>
     * @param <Cloneable>
     * @return
     */
    private static <T extends Serializable,Cloneable> T getMethodClone(T t){

        if(t!=null){


        }

        return null;
    }



}
