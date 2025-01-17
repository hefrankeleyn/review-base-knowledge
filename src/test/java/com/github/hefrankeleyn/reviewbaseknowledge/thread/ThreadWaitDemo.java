package com.github.hefrankeleyn.reviewbaseknowledge.thread;

import org.junit.Test;

/**
 * @Date 2025/1/17
 * @Author lifei
 */
public class ThreadWaitDemo {
    private boolean flag = false;

    private void runWait() {
        try {
            this.wait();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void product(int i) {
        synchronized (this) {
            if (flag) {
//                System.out.println("===> 已经有一个产品了，等着消费");
                runWait();
            }
            System.out.printf("==> 生产第%s个产品\n", i);
            flag = true;
            this.notifyAll();
        }
    }

    private void consume(int i) {
        synchronized (this) {
            if (!flag) {
//                System.out.println("===> 还没有生产，等待生产！");
                runWait();
            }
            System.out.printf("==> 消费第%s个产品\n", i);
            flag = false;
            this.notifyAll();
        }
    }

    @Test
    public void threadWaitTest() {
        Thread thread01 = new Thread(()->{
            for (int i = 1; i < 10; i++) {
                product(i);
            }
        });
        thread01.start();
        Thread thread02 = new Thread(()->{
            for (int i = 1; i < 10; i++) {
                consume(i);
            }
        });
        thread02.start();
        while (Thread.activeCount()>1) {
            Thread.yield();
        }
    }
}
