package com.demo.test;

import com.demo.entity.User;
import com.demo.impl.AopDemoInt;
import com.demo.impl.EnumDemo;
import com.demo.utils.CloneUtils;
import com.demo.utils.EnumUtils;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.function.Consumer;

public class EnumTest1 {

    public static void main(String[] args) {

        EnumDemo enumDemo = EnumUtils.getEnum(EnumDemo.class, "apply".toUpperCase());
        if(enumDemo!=null){
            System.out.println(enumDemo.gerPath());
        }else{
            System.out.println("null");
        }
        EnumDemo value = EnumDemo.valueOf("SIGN");
        System.out.println(value.gerPath());

        List<String> enumNameList = EnumUtils.getEnumNameList(EnumDemo.class);
        for (String str:enumNameList){
            System.out.println(str);
        }

        User user = new User("likepear", "520", "女", 22);
/*      Consumer consumer = System.out::println;
        consumer.accept(user.getUsername());
*/
        AopDemoInt proxyInstance = (AopDemoInt) Proxy.newProxyInstance(user.getClass().getClassLoader(), user.getClass().getInterfaces(),
                (proxy, method, arg2) -> {
                    System.out.println("前置通知");
                    Object obj = method.invoke(user,arg2);
                    System.out.println("后置通知");
                    return obj;
                });

        System.out.println(proxyInstance.getSecret());

        AopDemoInt clone = CloneUtils.getClone(user);
        User user2 = (User)clone;
        System.out.println(user2);

    }

}
