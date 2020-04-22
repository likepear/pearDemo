package com.demo.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class StreamDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("c");
        list.add("k");
        list.add("o");
        list.add("z");
        list.add("a");
        /*list.stream();//返回串行流*/
/*      Stream<String> stream = list.parallelStream(); //返回并行流

        -------------------------------------------
        Stream<String> m = stream.filter((str) -> {
            return str.compareTo("m") <= 0;
        });
        Iterator<String> iterator = m.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }*/

        /*-----------------------------------*/

/*        list.stream().filter((str)->{
            return str.compareTo("m")<=0;
        }).forEach((String str) -> {
            System.out.println(str);
        });*/

    list.add("k");
    list.add("a");
    list.stream().filter((str)->{ return str.compareTo("m")<=0; }).distinct().forEach((str) ->{
        System.out.println(str);
    });


    }
}
