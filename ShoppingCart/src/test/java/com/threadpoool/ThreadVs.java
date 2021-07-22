package com.threadpoool;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadVs {
    @Test
    public void oldHandle() throws InterruptedException {
        /**
         * 将world转换为PDF格式：处理时长很长的过程
         */
        /**
         * 使用循环来模拟许多用户请求的场景
         */
        for (int request = 0; request < 100; request++) {
            new Thread(() -> {
                System.out.println("文档处理开始！");
                try {
                    //将word转为pdf格式：处理时间较长的操作
                    Thread.sleep(1000L);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("文档处理结束");
            }).start();
        }
        Thread.sleep(1000L * 1000);

        }
    public void newHandler() throws InterruptedException {
        /**
         * 开启了一个线程池：线程个数是10个
         */
        ExecutorService threadPool =
                Executors.newFixedThreadPool(10);
        for (int request = 0; request < 100; request++) {
            threadPool.execute(() -> {
                System.out.println("文档处理开始！");
                try {
                    //将word转为pdf格式：处理时间较长的操作
                    Thread.sleep(10);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("文档处理结束");
            });
        }
        Thread.sleep(1000*1000);
    }

}

