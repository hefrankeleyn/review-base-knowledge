package com.github.hefrankeleyn.reviewbaseknowledge.thread;

import org.junit.Test;

/**
 * @Date 2025/1/17
 * @Author lifei
 */
public class ThreadJoinDemo {

    @Test
    public void threadJoinTest() {
        Thread thread01 = new Thread(()->{
            System.out.println("thread1: sleep 3s");
            ThreadDemoUtil.sleep(3000L);
            System.out.println("===> thread1: sleep ok , run continue!");
        });

        Thread thread02 = new Thread(()->{
            try {
                System.out.println("===> thread2: wait thread1");
                thread01.join();
                System.out.println("===> thread2 run ok!");
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        thread01.start();
        thread02.start();
        while (Thread.activeCount()>1) {
            Thread.yield();
        }
        System.out.println("...thread all ok!");

    }
}
