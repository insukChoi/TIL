package com.insuk.reactor.ch14.creating;

import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Arrays;
import java.util.List;

/**
 * Create_1() Operator는 generate() Operator처럼 프로그래밍 방식으로 Signal 이벤트를 발생시킨다.
 * generate() Operator는 데이터를 동기적으로 한 번에 한 건씩 emit 할 수 있는 반면에,
 * create() Operator는 한 번에 여러 건의 데이터를 비동기적으로 emit할 수 있습니다.
 */
public class Create_1 {
    private static final Logger log = LoggerFactory.getLogger(Create_1.class);
    static int SIZE = 0;
    static int COUNT = -1;
    final static List<Integer> DATA_SOURCE = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    public static void main(String[] args) {
        log.info("# start");
        Flux.create((FluxSink<Integer> sink) -> {
            sink.onRequest(n -> {
                try {
                    Thread.sleep(1000L);
                    for (int i = 0; i < n; i++) {
                        if (COUNT >= 9) {
                            sink.complete();
                        } else  {
                            COUNT++;
                            sink.next(DATA_SOURCE.get(COUNT));
                        }
                    }
                } catch (InterruptedException e) {}
            });

            sink.onDispose(() -> log.info("# clean up"));
        }).subscribe(new BaseSubscriber<>() {
            @Override
            protected void hookOnSubscribe(final Subscription subscription) {
                request(2);
            }

            @Override
            protected void hookOnNext(final Integer value) {
                SIZE++;
                log.info("# onNext: {}", value);
                if (SIZE == 2) {
                    request(2);
                    SIZE = 0;
                }
            }

            @Override
            protected void hookOnComplete() {
                log.info("# onComplete");
            }
        });
    }
}
