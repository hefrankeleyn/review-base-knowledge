package com.github.hefrankeleyn.reviewbaseknowledge.thread;

/**
 * @Date 2025/1/17
 * @Author lifei
 */
public class ThreadDemoUtil {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
