package com.juc.demo.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorsDemo {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(7);
        for(int i=0;i<10;i++){
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName());
            });
        }

        TimeUnit.SECONDS.sleep(2);
        executorService.shutdown();

    }

}
