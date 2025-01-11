package com.insuk.reactor.ch9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.stream.IntStream;

/**
 * reactor 에서 프로그래밍 방식으로 Signal 을 전송하는 가장 일반적인 방법은
 * generate() Operator 나 create() Operator 등을 사용하는 것인데,
 * 이는 Reactor 에서 Sinks 를 지원하기 전부터 이미 사용하던 방식이다.
 */
public class CreateOperator {
    private static final Logger log = LoggerFactory.getLogger(CreateOperator.class);

    public static void main(String[] args) throws InterruptedException {
        int tasks = 6;
        Flux.create(sink -> {
            IntStream.range(1, tasks)
                    .forEach(n -> sink.next(doTask(n)));
        }).subscribeOn(Schedulers.boundedElastic())
                .doOnNext(n -> log.info("# create(): {}", n))
                .publishOn(Schedulers.parallel())
                .map(result -> result + " success!")
                .doOnNext(n -> log.info("# map(): {}", n))
                .publishOn(Schedulers.parallel())
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(500L);
    }

    private static String doTask(int taskNumber) {
        // now tasking
        // complete to task
        return "task " + taskNumber + " result";
    }
}
/*
14:08:16.316 [boundedElastic-1] INFO com.insuk.reactor.ch9.CreateOperator -- # create(): task 1 result
14:08:16.318 [boundedElastic-1] INFO com.insuk.reactor.ch9.CreateOperator -- # create(): task 2 result
14:08:16.318 [boundedElastic-1] INFO com.insuk.reactor.ch9.CreateOperator -- # create(): task 3 result
14:08:16.318 [boundedElastic-1] INFO com.insuk.reactor.ch9.CreateOperator -- # create(): task 4 result
14:08:16.318 [boundedElastic-1] INFO com.insuk.reactor.ch9.CreateOperator -- # create(): task 5 result
14:08:16.318 [parallel-2] INFO com.insuk.reactor.ch9.CreateOperator -- # map(): task 1 result success!
14:08:16.318 [parallel-2] INFO com.insuk.reactor.ch9.CreateOperator -- # map(): task 2 result success!
14:08:16.318 [parallel-2] INFO com.insuk.reactor.ch9.CreateOperator -- # map(): task 3 result success!
14:08:16.318 [parallel-2] INFO com.insuk.reactor.ch9.CreateOperator -- # map(): task 4 result success!
14:08:16.318 [parallel-1] INFO com.insuk.reactor.ch9.CreateOperator -- # onNext: task 1 result success!
14:08:16.318 [parallel-2] INFO com.insuk.reactor.ch9.CreateOperator -- # map(): task 5 result success!
14:08:16.318 [parallel-1] INFO com.insuk.reactor.ch9.CreateOperator -- # onNext: task 2 result success!
14:08:16.318 [parallel-1] INFO com.insuk.reactor.ch9.CreateOperator -- # onNext: task 3 result success!
14:08:16.318 [parallel-1] INFO com.insuk.reactor.ch9.CreateOperator -- # onNext: task 4 result success!
14:08:16.318 [parallel-1] INFO com.insuk.reactor.ch9.CreateOperator -- # onNext: task 5 result success!
 */

/**
 * 위 코드에서 작업을 처리한 후, 그 결과 값을 반환하는 doTask() 메서드가 싱글스레드가 아닌 여러 개의 스레드에서
 * 각각의 전혀 다른 작업들을 처리한 후, 처리 결과를 반환하는 상황이 발생할 수도 있습니다.
 * 이 같은 상황에서 적절하게 사용할 수 있는 방식이 바로 Sinks 이다.
 */
