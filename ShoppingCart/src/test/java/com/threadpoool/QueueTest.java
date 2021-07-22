package com.threadpoool;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;

public class QueueTest {
    @Test
    public void testlimited() throws InterruptedException {
        /**
         * 基于数组的有界队列，队列容量为10
         */
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
        /**
         * 循环向队列添加元素
         */
        for (int i = 0; i < 20; i++) {
            queue.put(i);
            System.out.println("像队列中添加值" + i);
        }
    }
    @Test
    public void test() throws InterruptedException {
        /**
         * 基于链表的有界队列/无界，队列容量为10
         */
        LinkedBlockingDeque<Integer> queue = new LinkedBlockingDeque<>();
        /**
         * 循环向队列添加元素
         */
        for (int i = 0; i < 20; i++) {
            queue.put(i);
            System.out.println("像队列中添加值" + i);
        }
    }
    @Test
    public void testSynchronous() throws InterruptedException {
        /**
         * 同步移交阻塞队列
         */
        SynchronousQueue queue = new SynchronousQueue<Integer>();

        //插入值
        new Thread(() -> {
            try{
                queue.put(1);
                System.out.println("added");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();
        /*
        //删除值
        new Thread(() -> {
            try{
                queue.take();
                System.out.println("deleted");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();

         */
        Thread.sleep(1000);

    }
}
