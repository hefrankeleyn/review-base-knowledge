package com.github.hefrankeleyn.reviewbaseknowledge.thread;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

/**
 * @Date 2025/1/17
 * @Author lifei
 */
public class ThreadLockSupportDemo {

    @Test
    public void lockSupportTest() {
        Runnable run01 = ()->{
            System.out.println("===> ready park...");
            LockSupport.park();
            System.out.println("===> sub thread ok!");
        };
        Thread thread = new Thread(run01);
        thread.start();
        try {
            Thread.sleep(3000l);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("====>主线程发放许可证");
        LockSupport.unpark(thread);

    }
}
