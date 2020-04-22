package com.likepear.test;

import com.likepear.Utils.CloneUtils;
import com.likepear.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Method;

public class HelloTest {

    @Test
    public void test(){

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IocTest bean = (IocTest) context.getBean("helloTest");
        bean.test();

        User user = new User("likepear", "520", "女", 22);
/*        //创建cglib代理
        Enhancer enhancer = new Enhancer();
        //设置代理对象类加载器
        enhancer.setClassLoader(user.getClass().getClassLoader());
        //设置代理对象的父类
        enhancer.setSuperclass(user.getClass());
        //设置回调方法
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("代理了"+method.getName()+"方法！");
                Object invoke = method.invoke(user, objects);
                return invoke;
            }
        });

        //生成代理对象
        User proxyUser = (User) enhancer.create();

        System.out.println(proxyUser);*/

        User clone = CloneUtils.getClone(user);
        System.out.println(clone);
        System.out.println(clone.getUsername());

    }

}
