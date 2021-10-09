package com.example.demo;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
class DemoApplicationTests {


    @Test
    void test() {
        guavaRetry();

    }


    private static void guavaRetry() {
        Retryer<Integer> retryer = RetryerBuilder.<Integer>newBuilder()
                .retryIfResult(Predicates.isNull())
                .retryIfResult(Predicates.equalTo(2))
                .retryIfExceptionOfType(IndexOutOfBoundsException.class)
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .withWaitStrategy(WaitStrategies.fixedWait(1,TimeUnit.SECONDS))
                .withRetryListener(new RetryListener() {
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {
                        log.info("listening...");
                        if(attempt.hasException()){
                            log.error("RetryListener exception:{}",attempt.getExceptionCause());
                        }
                    }
                })
                .build();

        try {
            retryer.call(()->{
                return  2;
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RetryException e) {
            e.printStackTrace();
        }


    }

}
