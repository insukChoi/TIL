package com.insuk.reactor.ch9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class Sinks_SinksOne {
    private static final Logger log = LoggerFactory.getLogger(Sinks_SinksOne.class);

    public static void main(String[] args) {
        Sinks.One<String> sinkOne = Sinks.one();
        Mono<String> mono = sinkOne.asMono();

        sinkOne.emitValue("Hello Reactor", Sinks.EmitFailureHandler.FAIL_FAST);
//        sinkOne.emitValue("Hi Reactor", Sinks.EmitFailureHandler.FAIL_FAST);

        mono.subscribe(data -> log.info("# Subscriber1 ", data));
        mono.subscribe(data -> log.info("# Subscriber2 ", data));
    }
}
/*
// Sinks.One 으로 emit 한 데이터가 두 구독자에게 모두 전달되었습니다.
14:43:33.313 [main] INFO com.insuk.reactor.ch9.Sinks_SinksOne -- # Subscriber1
14:43:33.315 [main] INFO com.insuk.reactor.ch9.Sinks_SinksOne -- # Subscriber2
 */
/**
 * 위 주석을 해제해서 실행하면, "Hi Reactor" 는 drop 된다.
 * 처음 emit 한 데이터는 정상적으로 emit 되지만 나머지 데이터들은 Drop 된다.
 */