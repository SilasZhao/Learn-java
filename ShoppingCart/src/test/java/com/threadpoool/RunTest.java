package com.threadpoool;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RunTest {
    @Test
    public void submitTest() throws ExecutionException, InterruptedException {
        //创建线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        /**
         * 利用submit方法提交任务，接受任务的返回结果
         */
        Future<Integer> future = threadPool.submit(() -> {
            Thread.sleep(1000 * 10);
            return 2* 5;
        });
        /**
         * 阻塞方法，知道任务有返回值后，才向下执行
         */
        Integer num = future.get();
        System.out.println("执行结果： " + num);
    }
    @Test
    public void executeTest() throws InterruptedException {
        //创建线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.execute(() -> {
            try {
                Thread.sleep(1000L* 3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Integer num = 2*5;
            System.out.println("result: " + num);
        });
        Thread.sleep(1000*3);
    }

}
