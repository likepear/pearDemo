package com.demo.reflect;

import com.demo.reflect.inf.MapperInterface;
import com.demo.reflect.ov.MapperDao;

import java.lang.reflect.Method;

public class MethodDemo {

    public static void main(String[] args) throws NoSuchMethodException {

        Method[] methods = MapperInterface.class.getMethods(); //接口的method
        Method method = MapperDao.class.getMethod("hashCode",null);

        //false接口类型的class与object不同
        System.out.println(Object.class.equals(methods[0].getDeclaringClass()));
        //true 调用的是object类中的方法时class是object
        System.out.println(Object.class.equals(method.getDeclaringClass()));

    }
}
