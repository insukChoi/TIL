package com.insuk.reactor.ch14;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * generate()의 첫번째 파라미터에서 초깃값을 0으로 지정했으며, 두번째 파라미터에서 전달받은 SynchronousSink 객체로 상태값을 emit합니다.
 * SynchronousSink는 하나의 Signal만 동기적으로 발생시킬 수 있으며 최대 하나의 상태 값만 emit하는 인터페이스입니다.
 */
public class Generate_1 {
    private static final Logger log = LoggerFactory.getLogger(Generate_1.class);

    public static void main(String[] args) {
        Flux.generate(() -> 0, (state, sink) -> {
            sink.next(state);
            if (state == 10) {
                sink.complete();
            }
            return ++state;
        })
                .subscribe(data -> log.info("# onNext: {}", data));
    }
}
/*
17:10:26.028 [main] INFO com.insuk.reactor.ch14.Generate_1 -- # onNext: 0
17:10:26.030 [main] INFO com.insuk.reactor.ch14.Generate_1 -- # onNext: 1
17:10:26.030 [main] INFO com.insuk.reactor.ch14.Generate_1 -- # onNext: 2
17:10:26.030 [main] INFO com.insuk.reactor.ch14.Generate_1 -- # onNext: 3
17:10:26.030 [main] INFO com.insuk.reactor.ch14.Generate_1 -- # onNext: 4
17:10:26.030 [main] INFO com.insuk.reactor.ch14.Generate_1 -- # onNext: 5
17:10:26.030 [main] INFO com.insuk.reactor.ch14.Generate_1 -- # onNext: 6
17:10:26.030 [main] INFO com.insuk.reactor.ch14.Generate_1 -- # onNext: 7
17:10:26.030 [main] INFO com.insuk.reactor.ch14.Generate_1 -- # onNext: 8
17:10:26.031 [main] INFO com.insuk.reactor.ch14.Generate_1 -- # onNext: 9
17:10:26.031 [main] INFO com.insuk.reactor.ch14.Generate_1 -- # onNext: 10
 */