package com.juc.demo.base;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReadWriteLock;

public class VilotaleDemo implements Runnable {

    private static volatile int i = 0;

    @Override
    public void run() {
        add();
    }

    public synchronized void add(){

        for(int j=0;j<10000;j++){
            i++;
            //StampedLock
            //CyclicBarrier 所有线程就绪才执行
            //ReadWriteLock
            //Semaphore 限流
            //Exchanger
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new VilotaleDemo());
        Thread thread1 = new Thread(new VilotaleDemo());
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println("i的值为："+i);
    }

}
