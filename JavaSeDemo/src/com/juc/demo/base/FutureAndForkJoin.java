package com.juc.demo.base;

import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

import static java.lang.Math.sqrt;

/**
 * 任务拆分，结果汇总
 */
public class FutureAndForkJoin {

    /**
     * recursiveAction没有返回结果
     */
    static class CompNoResult extends RecursiveAction{

        @Override
        protected void compute() {

        }
    }


    /**
     * recursiveAction没有返回结果
     */
    static final class CompResult extends RecursiveTask<Long> {

        private final long start; //开始索引
        private final long end; //结束索引

        CompResult(long st,long ed){
            this.start = st;
            this.end = ed;
        }

        @Override
        protected Long compute() {
            if(end-start<=10000){
                long sum = 0L;
                for(long i=start;i<end;i++){
                    sum += i;
                }
                System.out.println("from "+start+" to "+end);
                return sum;
            }

           long middle = start + (end-start)/2;
           CompResult compResult1 = new CompResult(start,middle);
           CompResult compResult2 = new CompResult(middle,end);
           compResult1.fork();
           compResult2.fork();

           return compResult1.join()+compResult2.join();

        }

    }

    public static void main(String[] args) throws IOException {

        ForkJoinPool fjp = new ForkJoinPool();  //执行任务使用的是守护线程
        CompResult result = new CompResult(0l, 920000);
        fjp.execute(result);
        Long join = result.join();
        System.out.println(join);

        System.in.read(); //因为使用的是守护线程，为了观察结果。
    }

}
