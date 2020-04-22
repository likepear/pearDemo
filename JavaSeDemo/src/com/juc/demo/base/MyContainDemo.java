package com.juc.demo.base;

import sun.misc.Lock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 容器demo
 */
public class MyContainDemo {

    private volatile List<String> list = new ArrayList();

    private final static Object lock = new Object();

    public int size(){
        return list.size();
    }

    public void add(String x){
        list.add(x);
    }

    public static void main(String[] args) throws InterruptedException {

        MyContainDemo myContainDemo = new MyContainDemo();

        Thread thread2 = new Thread(()->{
            System.out.println("thread2开始");
            synchronized (lock){
                if(myContainDemo.size()<5){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("---thread2结束---");
                lock.notify(); //唤醒线程1
            }

        });
        thread2.start();

        Thread.sleep(1000);

        Thread thread = new Thread(()->{
            for(int i=0;i<10;i++){
                synchronized (lock){
                    myContainDemo.add(""+i);
                    System.out.println("thread1添加"+i);
                    if(myContainDemo.size()==5){
                        lock.notify(); //唤醒线程2
                        try {
                            System.out.println("线程1 wait...");
                            lock.wait(); //线程1等待
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            System.out.println("---线程1结束---");
        });
        thread.start();
    }
}
