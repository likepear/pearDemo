package com.likepear.Utils;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;

import java.io.Serializable;
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
    public static <T> T getClone(T t){

        T proxy = getProxy2(t);

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
     * 返回目标类的动态代理类
     * @param t
     * @param <T>
     * @return
     */
    private static <T> T getProxy2(T t){

        if(t!=null){

            Enhancer enhancer = new Enhancer();
            enhancer.setClassLoader(t.getClass().getClassLoader());
            enhancer.setSuperclass(t.getClass());
            enhancer.setCallback(new InvocationHandler() {
                @Override
                public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                    System.out.println("代理了"+method.getName()+"方法！");
                    Object invoke = method.invoke(t, objects);
                    return invoke;
                }
            });

            T proxyObj = (T) enhancer.create();

            return (T) proxyObj;

        }

        return null;

    }

}
