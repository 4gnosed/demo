package com.example.demo.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Package: com.example.demo.concurrent
 * @Description:    只允许最多两个线程同时访问，超过两个线程将被阻塞
 * @Author: LuDeSong
 * @Date: 2022-1-5 18:53
 */

public class TwinsLock implements Lock {

    private final Sync sync = new Sync(2);

    public int count(){
        return sync.count();
    }

    private static final class Sync extends AbstractQueuedSynchronizer {
        Sync(int count) {
            if (count < 0) {
                throw new IllegalArgumentException("count必须大于0");
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int count) {
            for (; ; ) {
                int current = getState();
                int newCount = current - count;
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int count) {
            for (; ; ) {
                int current = getState();
                int newCount = current + count;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }

        protected int count() {
            return getState();
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }
    }


    @Override
    public void lock() {
        sync.tryAcquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.tryReleaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
