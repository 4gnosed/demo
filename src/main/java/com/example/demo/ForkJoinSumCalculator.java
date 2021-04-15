package com.example.demo;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @Package: com.example.demo
 * @Description: 用分支/合并框架执行并行求和
 * @Author: LuDeSong
 * @Date: 2021-4-15 14:18
 */

public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;
    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        ForkJoinSumCalculator leftTask =
                new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();//利用另一个 ForkJoinPool 线程异步执行新创建的子任务
        ForkJoinSumCalculator rightTask =
                new ForkJoinSumCalculator(numbers, start + length / 2, end);
        Long rightResult = rightTask.compute();//同步执行第二个子任务，有可能允许进一步递归划分
        Long leftResult = leftTask.join();//读取第一个子任务的结果，如果尚未完成就等待
        return leftResult + rightResult;//该任务的结果是两个子任务结果的组合
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }


    /**
     * 2亿个数求和
     * 140:20000000100000000
     * 340:20000000100000000
     *
     * @param args
     */
    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 200_000_000).toArray();

        long startTime = System.currentTimeMillis();
        ForkJoinSumCalculator task = new ForkJoinSumCalculator(numbers, 0, numbers.length);
        Long sum = task.compute();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime + ":" + sum);

        long startTime1 = System.currentTimeMillis();
        long sum1 = task.computeSequentially();
        long endTime1 = System.currentTimeMillis();
        System.out.println(endTime1 - startTime1 + ":" + sum1);

    }
}
