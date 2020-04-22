package com.juc.demo;

/**
 * 保证线程有序运行
 * 使用join方法
 * 会使main线程wait
 */
public class ThreadSort {

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(()->{
            System.out.println("thread 1!");
        });

        Thread thread2 = new Thread(()->{
            System.out.println("thread 2!");
        });

        Thread thread3 = new Thread(()->{
            System.out.println("thread 3!");
        });

/*      此时线程1 2 3的执行顺序是无序的
        thread1.start();
        thread2.start();
        thread3.start();*/

        /**
         *  使用join暂时主线程来保持线程的有序执行
         *  在主线程中调用thread1的join方法，此时当前线程仍然指的是主线程
         *  thread1只是当作一个普通的对象使用，在thread1对象里调用wait方法，等待的仍然是主线程
         */
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
        thread3.join();

    }

}
