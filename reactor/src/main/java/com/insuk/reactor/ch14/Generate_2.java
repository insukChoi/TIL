package com.insuk.reactor.ch14;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

public class Generate_2 {
    private static final Logger log = LoggerFactory.getLogger(Generate_2.class);

    public static void main(String[] args) {
        final int dan = 3;
        Flux.generate(() -> Tuples.of(dan, 1), (state, sink) -> {
            sink.next(state.getT1() + " * " + state.getT2() + " = " + state.getT1() * state.getT2());
            if (state.getT2() == 9)
                sink.complete();
            return Tuples.of(state.getT1(), state.getT2() + 1);
        }, state -> log.info("# 구구단 {}단 종료!", state.getT1()))
                .subscribe(data -> log.info("# onNext: {}", data));
    }
}
