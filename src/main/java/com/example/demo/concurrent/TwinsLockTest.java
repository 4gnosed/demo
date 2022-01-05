package com.example.demo.concurrent;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @Package: com.example.demo.concurrent
 * @Description:
 * @Author: LuDeSong
 * @Date: 2022-1-5 19:20
 */

public class TwinsLockTest {
    public static void main(String[] args) throws InterruptedException {
        TwinsLock lock = new TwinsLock();
        class worker extends Thread {
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + "剩余count：" + lock.count());
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            worker worker = new worker();
            worker.start();
        }

    }
}
