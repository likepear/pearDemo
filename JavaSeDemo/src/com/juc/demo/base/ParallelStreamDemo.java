package com.juc.demo.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 并行流demo
 */
public class ParallelStreamDemo {

    public static void main(String[] args) throws InterruptedException {

        List<Long> list = new ArrayList<>();
        for(long i=0;i<9999999;i++){
            list.add(i);
        }

        long startTime = System.currentTimeMillis();
        list.stream().forEach(ParallelStreamDemo::isPrime);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);

        Thread.sleep(1000);

        long startTime2 = System.currentTimeMillis();
        list.parallelStream().forEach((v)->{
            isPrime(v);
        });
        long endTime2 = System.currentTimeMillis();
        System.out.println(endTime2-startTime2);

    }

    public static boolean isPrime(long num){

        for(long i=num;i<num/2;i++){

            if(num%2==0)
                return false;
            else
                return true;

        }

        return false;

    }

}
