package com.juc.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureAndCallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                return "callable1 运行！";
            }
        };

        Callable callable2 = new Callable() {
            @Override
            public Object call() throws Exception {
                return "callable2 运行！";
            }
        };

        FutureTask future = new FutureTask(callable);
        FutureTask future2 = new FutureTask(callable2);

        Thread thread1 = new Thread(future);
        Thread thread2 = new Thread(future2);
        thread1.start();
        thread2.start();

        System.out.println(future.get());
        System.out.println(future2.get());

    }

}
