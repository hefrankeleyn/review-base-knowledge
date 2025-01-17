package com.github.hefrankeleyn.reviewbaseknowledge.thread;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Date 2025/1/17
 * @Author lifei
 */
public class CreateThreadDemo {

    private static final Logger log = LoggerFactory.getLogger(CreateThreadDemo.class);
    private boolean flag = false;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private void product(int i) {
        lock.lock();
        try {
            if (flag) {
                condition.await();
            }
            System.out.printf("==> 生成第%s条数据\n", i);
            flag = true;
            condition.signalAll();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }

    private void consumer(int i) {
        lock.lock();
        try {
            if (!flag) {
                condition.await();
            }
            System.out.printf(">> 消费第%s条数据\n", i);
            flag = false;
            condition.signalAll();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }

    @Test
    public void createThreadTest01() {
        Runnable run01 = ()->{
            for (int i = 1; i < 10; i++) {
                product(i);
            }
        };
        Runnable run02 = ()->{
            for (int i = 1; i < 10; i++) {
                consumer(i);
            }
        };
        new Thread(run01).start();
        new Thread(run02).start();
        while (Thread.activeCount()>1) {
            Thread.yield();
        }
    }
}
