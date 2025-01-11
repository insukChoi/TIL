package com.insuk.reactor.ch9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

public class Sinks_1 {
    private static final Logger log = LoggerFactory.getLogger(Sinks_1.class);

    public static void main(String[] args) throws InterruptedException {
        int tasks = 6;

        Sinks.Many<String> unicastSinks = Sinks.many().unicast().onBackpressureBuffer();
        Flux<String> fluxView = unicastSinks.asFlux();
        IntStream.range(1, tasks)
                .forEach(n -> {
                    try {
                        new Thread(() -> {
                            unicastSinks.emitNext(doTask(n), Sinks.EmitFailureHandler.FAIL_FAST);
                            log.info("# emitted: {}", n);
                        }).start();
                        Thread.sleep(100L);
                    } catch (InterruptedException e) {
                        log.error(e.getMessage());
                    }
                });

        fluxView.publishOn(Schedulers.parallel())
                .map(result -> result + " success!")
                .doOnNext(n -> log.info("# map(): {}", n))
                .publishOn(Schedulers.parallel())
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(200L);
    }

    static String doTask(int taskNumber) {
        // now tasking
        // complete to task
        return "task " + taskNumber + " result";
    }
}
/*
14:24:44.134 [Thread-0] INFO com.insuk.reactor.ch9.Sinks_1 -- # emitted: 1
14:24:44.235 [Thread-1] INFO com.insuk.reactor.ch9.Sinks_1 -- # emitted: 2
14:24:44.338 [Thread-2] INFO com.insuk.reactor.ch9.Sinks_1 -- # emitted: 3
14:24:44.443 [Thread-3] INFO com.insuk.reactor.ch9.Sinks_1 -- # emitted: 4
14:24:44.548 [Thread-4] INFO com.insuk.reactor.ch9.Sinks_1 -- # emitted: 5
14:24:44.692 [parallel-2] INFO com.insuk.reactor.ch9.Sinks_1 -- # map(): task 1 result success!
14:24:44.692 [parallel-2] INFO com.insuk.reactor.ch9.Sinks_1 -- # map(): task 2 result success!
14:24:44.692 [parallel-2] INFO com.insuk.reactor.ch9.Sinks_1 -- # map(): task 3 result success!
14:24:44.692 [parallel-2] INFO com.insuk.reactor.ch9.Sinks_1 -- # map(): task 4 result success!
14:24:44.692 [parallel-1] INFO com.insuk.reactor.ch9.Sinks_1 -- # onNext: task 1 result success!
14:24:44.692 [parallel-2] INFO com.insuk.reactor.ch9.Sinks_1 -- # map(): task 5 result success!
14:24:44.692 [parallel-1] INFO com.insuk.reactor.ch9.Sinks_1 -- # onNext: task 2 result success!
14:24:44.692 [parallel-1] INFO com.insuk.reactor.ch9.Sinks_1 -- # onNext: task 3 result success!
14:24:44.692 [parallel-1] INFO com.insuk.reactor.ch9.Sinks_1 -- # onNext: task 4 result success!
14:24:44.692 [parallel-1] INFO com.insuk.reactor.ch9.Sinks_1 -- # onNext: task 5 result success!
 */
/**
 * 코드 실행 결과를 보면, doTask() 메서드는 루프를 돌 때마다 새로운 스레드에서 실행되기 때문에
 * 총 5개의 스레드(Thread-0 ~ Thread-4)에서 실행되고, map() Operator 에서의 가공 처리는 parallel-2 스레드,
 * Subscriber 에서 전달받은 데이터의 처리는 parallel-1 스레드에서 실행되어 총 7개의 스레드가 실행되었습니다.
 *
 * 이처럼 Sinks 는 프로그래밍 방식으로 Signal 을 전송할 수 있으며, 멀티스레드 환경에서
 * 스레드 안정성(Thread Safety)을 보장받을 수 있는 장점이 있다.
 */