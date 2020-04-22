package com.juc.demo.base;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者与消费者demo
 * 支持多消费者和多生产者
 */
public class ProAndConsumer<T> implements Runnable{

    private int max_value = 10;
    private int count = 0;
    private volatile AtomicInteger state = new AtomicInteger(0);
    private static Lock lock = new ReentrantLock();
    private static Condition product = lock.newCondition();
    private static Condition consumer = lock.newCondition();

    private LinkedList<T> list = new LinkedList<>();

    public int size(){
        return list.size();
    }

    public AtomicInteger getState(){
        return state;
    }

    public int getStateValue(){
        return state.get();
    }

    private void add(T t){
        list.addFirst(t);
    }

    @Override
    public void run() {
        lock.lock();
        try{

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void put(T t) {
        lock.lock();
        try{
            while (count==max_value){
                product.await(); //生产者等待
            }

            list.add(t);
            System.out.println("生产"+t);
            count++;
            consumer.signalAll(); //唤醒消费者

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public T get(){
        lock.lock();
        try {

            while (count==0){
                consumer.await(); //消费者等待
            }
            T t = list.getFirst();
            System.out.println("消费"+t);
            count--;
            product.signalAll();//唤醒生产者
            return t;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {

        ProAndConsumer proAndConsumer = new ProAndConsumer();

        for(int i=0;i<4;i++){
            new Thread(()->{
                for(int j=0;j<10;j++){
                    proAndConsumer.get();
                }
            }).start();
        }

        for(int i=0;i<2;i++){ //生产者
            new Thread(()->{
                for(int j=0;j<20;j++){
                    proAndConsumer.put(Thread.currentThread().getName()+j);
                }

            }).start();
        }

    }

}
