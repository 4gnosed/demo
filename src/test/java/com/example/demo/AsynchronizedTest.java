package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @Package: com.example.demo
 * @Description:异步训练
 * @Author: LuDeSong
 * @Date: 2021-3-31 18:45
 */
@Slf4j
@SpringBootTest
public class AsynchronizedTest {

    private static final ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 10, 100L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(5), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("my-thread");
            return thread;
        }
    }, new ThreadPoolExecutor.AbortPolicy());

    /**
     * thenCombine 会把 两个 CompletionStage 的任务都执行完成后，把两个任务的结果一块交给 thenCombine 来处理。
     *
     * @throws Exception
     */
    @Test
    public void thenCombine() throws Exception {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return Integer.valueOf(100);
            }
        });
        CompletableFuture<Boolean> future2 = CompletableFuture.supplyAsync(new Supplier<Boolean>() {
            @Override
            public Boolean get() {
                return Boolean.TRUE;
            }
        });
        CompletableFuture<String> result = future1.thenCombine(future2, new BiFunction<Integer, Boolean, String>() {
            @Override
            public String apply(Integer t, Boolean u) {
                return t.toString() + u.toString();
            }
        });
        log.info("" + result.get());
    }

    /**
     * 两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的转化操作。
     *
     * @throws Exception
     */
    @Test
    public void applyToEither() throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(5) + 1;
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("f1=" + t);
                return t;
            }
        });
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(5) + 1;
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("" + "f2=" + t);
                return t;
            }
        });

        CompletableFuture<Integer> result = f1.applyToEither(f2, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer t) {
                log.info("" + t);
                return t * 2;
            }
        });
//        CompletableFuture<Integer> result = f1.applyToEitherAsync(f2, new Function<Integer, Integer>() {
//            @Override
//            public Integer apply(Integer t) {
//                log.info("" + t);
//                return t * 2;
//            }
//        }, pool);

        log.info("result:" + result.get());
    }

    /**
     * handle 方法和 thenApply 方法处理方式基本一样。不同的是 handle 是在任务完成后再执行，还可以处理异常的任务。thenApply 只可以执行正常的任务，任务出现异常则不执行 thenApply 方法。
     */
    @Test
    public void handle() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {

            @Override
            public Integer get() {
                int r = new Random().nextInt(10);
                log.info("" + r);
                if ((r & 1) == 1) {
                    int i = 10 / 0;
                }
                return r;
            }
        }).handle(new BiFunction<Integer, Throwable, Integer>() {
            @Override
            public Integer apply(Integer param, Throwable throwable) {
                if (throwable == null) {
                    param = param * 2;
                } else {
                    log.info("" + throwable.getMessage());
                }
                return param;
            }
        });
        try {
            log.info("" + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 当一个线程依赖另一个线程时，可以使用 thenApply 方法来把这两个线程串行化
     */
    @Test
    public void thenApply() {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                long result = new Random().nextInt(100);
                log.info("" + "result1=" + result);
                return result;
            }
        }).thenApply(new Function<Long, Long>() {
            @Override
            public Long apply(Long t) {
                long result = t * 5;
                log.info("" + "result2=" + result);
                return result;
            }
        });

        long result = 0;
        try {
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        log.info("" + result);
    }

    /**
     * whenComplete 方法是在任务执行完成后再执行的任务,exceptionally 方法是在任务发生异常的时候执行的任务
     */
    @Test
    public void whenComplete() {
        //runAsync 不支持返回值
        //supplyAsync 支持返回值
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            if (new Random().nextInt(10) % 2 == 0) {
                int i = 12 / 0;
            }
            log.info("" + "run end ...");
            return "good";
        }).whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String t, Throwable action) {
                log.info("" + "执行完成！t=" + t);
            }

        }).exceptionally(new Function<Throwable, String>() {
            @Override
            public String apply(Throwable t) {
                log.info("" + "执行失败！" + t.getMessage());
                return null;
            }
        });
    }

    @Test
    public void testFuture() {
        Future<String> future = pool.submit(() -> {
            int i = 5;
            while (i > 0) {
                try {
                    log.info("" + i--);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "done!";
        });

        try {
            log.info("" + "执行结束：" + future.get(2, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            log.error("" + "超时了");
            e.printStackTrace();
        }
        log.info("" + "bottommmmmmmmmm");
    }
}
