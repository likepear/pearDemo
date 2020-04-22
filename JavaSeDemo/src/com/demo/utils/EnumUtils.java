package com.demo.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 枚举操作工具类
 * @author likepear
 * @version 0.1
 */
public class EnumUtils {

    public static <T extends Enum<T>> T getEnum(Class<T> tClass,String type){

        if(tClass==null||type==null)
            return null;

        boolean anEnum = tClass.isEnum();
        if(anEnum){
            T[] constants = tClass.getEnumConstants();
            String tName = "";
            for(T t:constants){
                tName = t.name();
                if(type.equals(tName)){
                    return t;
                }
            }

        }

        return null;

    }

    /**
     * 获取枚举对象数组
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T extends Enum<T>> T[] getEnumObjList(Class<T> tClass){

        if(tClass==null)
            return null;

        return tClass.getEnumConstants();

    }

    /**
     * 获取枚举常量名称列表
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T extends Enum<T>> List<String> getEnumNameList(Class<T> tClass){

        if(tClass==null)
            return null;

        T[] enumConstants = tClass.getEnumConstants();
        List<String> list = new ArrayList<>();
        for(T t:enumConstants){
            list.add(t.name());
        }

        return list;
    }

    /**
     * 调用枚举类的方法
     * @param tClass
     * @param mName 方法名
     * @param <T>
     * @return
     */
    public static <T extends Enum<T>> Object invokeMethod(Class<T> tClass,String mName){

        if(tClass==null||mName==null||mName.equals(""))
            return null;

        return null;
    }

}
