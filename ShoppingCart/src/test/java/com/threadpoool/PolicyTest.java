package com.threadpoool;

import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PolicyTest {
    private static ThreadPoolExecutor executor =

            new ThreadPoolExecutor(
                    //核心线程和最大线程数
                    2,3,
                    //线程空闲后存活的时间
                    60L, TimeUnit.SECONDS,
                    //有界阻塞队列
                    new LinkedBlockingQueue<Runnable>(5));

    class Task implements Runnable{
        /**
         * 任务名称
         */
       private String taskName;
       public Task(String taskName){
            this.taskName = taskName;
       }

       @Override
       public void run(){
           System.out.println("线程【" + Thread.currentThread().getName() + "】正在执行【" + this.taskName+ "】任务...");
           try{
               Thread.sleep(1000L );
           }catch (InterruptedException e){
               e.printStackTrace();
           }

           System.out.println("线程【" + Thread.currentThread().getName() + " 已执行完【" + this.taskName+ "】任务!");
       }
    }

    /**
     * 线程池的执行过程
     * 2个核心线程
     * 5个任务的队列
     * 3个最大线程：1个线程可用呢
     *
     * 前两个任务会占用核心线程
     * 第三个到第七个任务会暂存任务队列
     * 第八个任务会启动最大线程
     * 第九个会报错
     */
    @Test
    public void abortPolicyTest(){
        //设置饱和策略为终止策略
        executor.setRejectedExecutionHandler(
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 1; i < 11; i++) {

            try{
                //提交10个线程任务
                executor.execute(new Task("线程任务： " + i));
            }catch (Exception e){
                System.err.println(e);
            }
            //关闭线程池

        }
        executor.shutdown();
    }

    @Test
    public void discardPolicy(){
        //设置饱和策略为终止策略
        executor.setRejectedExecutionHandler(
                new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 1; i < 11; i++) {

            try{
                //提交10个线程任务
                executor.execute(new Task("线程任务： " + i));
            }catch (Exception e){
                System.err.println(e);
            }
            //关闭线程池

        }
        executor.shutdown();
    }
    @Test
    public void discardOldestPolicyTest(){
        //设置饱和策略为终止策略
        executor.setRejectedExecutionHandler(
                new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i = 1; i < 11; i++) {

            try{
                //提交10个线程任务
                executor.execute(new Task("线程任务： " + i));
            }catch (Exception e){
                System.err.println(e);
            }
            //关闭线程池

        }
        executor.shutdown();
    }
    @Test
    public void CallerRunsPolicyTest(){
        //设置饱和策略为终止策略
        executor.setRejectedExecutionHandler(
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 1; i < 11; i++) {

            try{
                //提交10个线程任务
                executor.execute(new Task("线程任务： " + i));
            }catch (Exception e){
                System.err.println(e);
            }
            //关闭线程池

        }
        executor.shutdown();
    }
    /**
     * 单元测试执行完毕，主线程等待100秒，防止主线程退出
     * @throws InterruptedException
     */
    @After
    public void after() throws InterruptedException{
        Thread.sleep(1000L*50);
    }

}
