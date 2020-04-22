package com.juc.demo.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    private volatile List<String> list = new ArrayList();

    private static CountDownLatch latch = new CountDownLatch(1);
    private static CountDownLatch latch2 = new CountDownLatch(1);

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
            if(myContainDemo.size()<5){
                try {
                    /**
                     * latch.await();
                     * 当latch的计数器值为0时，此线程才继续往下执行
                     */
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    latch2.countDown();
                }
            }
            System.out.println("---thread2结束---");

        });
        thread2.start();

        Thread.sleep(1000);

        Thread thread = new Thread(()->{
            for(int i=0;i<10;i++){
                    myContainDemo.add(""+i);
                    System.out.println("thread1添加"+i);
                    if(myContainDemo.size()==5){
                        //计数器减一
                        latch.countDown();
                        try {
                            latch2.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            }
            System.out.println("---线程1结束---");
        });
        thread.start();
    }

}
