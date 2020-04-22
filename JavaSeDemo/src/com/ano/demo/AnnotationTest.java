package com.ano.demo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationTest {

    @MyAnnotation(lengthMax = 10)
    private String name;
    @MyAnnotation(lengthMax = 16)
    private String password;

    public AnnotationTest(String name,String password){

        this.name = name;
        this.password = password;

    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        AnnotationTest test = new AnnotationTest("likepear", "likepear5200");
        Field[] fields = test.getClass().getDeclaredFields();
        if(fields!=null){
            for(Field field:fields){

                if(!field.isAccessible()){
                    field.setAccessible(true);
                }


                System.out.println(field.getName());

                MyAnnotation annotation = field.getAnnotation(MyAnnotation.class);
                if(annotation!=null){

                    Method[] methods = annotation.annotationType().getDeclaredMethods();
                    if(methods!=null){
                        for (Method method:methods){
                            Object invoke = method.invoke(annotation, null);
                            if(invoke!=null){
                                System.out.println(invoke);
                            }
                        }
                    }

                }
            }
        }


    }

}
