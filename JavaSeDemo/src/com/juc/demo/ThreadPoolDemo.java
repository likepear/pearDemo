package com.juc.demo;

import java.util.concurrent.*;

public class ThreadPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newCachedThreadPool();

        service.submit(()->{
            return "callable run!";
        });

        service.shutdown();


        long startTime =  System.currentTimeMillis();
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for(int i=0;i<50000;i++){
            final int x = i;
            executorService.execute(()->{
                if(x<5)
                    System.out.println("runable run!!!");
            });
        }

        long endTime = System.currentTimeMillis();

        System.out.println("用时:"+(endTime-startTime));

        executorService.shutdown();

        long startTime1 =  System.currentTimeMillis();

        for(int i=0;i<10000;i++){
            int x = i;
            Thread thread = new Thread(()->{
                if(x<3){
                    System.out.println("run!");
                }
            });
            thread.start();
            thread.join();
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("用时:"+(endTime1-startTime1));



    }

}
