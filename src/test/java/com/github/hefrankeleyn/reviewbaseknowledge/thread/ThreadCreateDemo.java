package com.github.hefrankeleyn.reviewbaseknowledge.thread;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Date 2025/1/17
 * @Author lifei
 */
public class ThreadCreateDemo {

    @Test
    public void createTest() {
        // 第一种方式
        Thread thread01 = new Thread(){
            @Override
            public void run() {
                System.out.println("===> extents Thread class");
            }
        };
        thread01.start();
        // 第二种方式
        Runnable run01 = ()->{
            System.out.println("===> import Runnable interface.");
        };
        new Thread(run01).start();
        // 第三种方式
        Callable<String> call01 = ()->{
            System.out.println("==> import Callable interface.");
            return "Callable ok!";
        };
        FutureTask<String> futureTask = new FutureTask<>(call01);
        new Thread(futureTask).start();
        try {
            String futureResult = futureTask.get();
            System.out.println("===> futureResult: " + futureResult);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        while (Thread.activeCount()>1) {
            Thread.yield();
        }

    }
}
