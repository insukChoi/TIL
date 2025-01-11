package com.insuk.reactor.ch9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Sinks_SinksMany_multicast_replay {
    private static final Logger log = LoggerFactory.getLogger(Sinks_SinksMany_multicast_replay.class);

    public static void main(String[] args) {
        Sinks.Many<Integer> multicastSink = Sinks.many().replay().limit(2);
        Flux<Integer> fluxView = multicastSink.asFlux();

        multicastSink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        multicastSink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);
        multicastSink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> log.info("# Subscriber1: {}", data));

        multicastSink.emitNext(4, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> log.info("# Subscriber2: {}", data));
    }
}
/*
15:23:14.972 [main] INFO com.insuk.reactor.ch9.Sinks_SinksMany_multicast_replay -- # Subscriber1: 2
15:23:14.974 [main] INFO com.insuk.reactor.ch9.Sinks_SinksMany_multicast_replay -- # Subscriber1: 3
15:23:14.974 [main] INFO com.insuk.reactor.ch9.Sinks_SinksMany_multicast_replay -- # Subscriber1: 4
15:23:14.974 [main] INFO com.insuk.reactor.ch9.Sinks_SinksMany_multicast_replay -- # Subscriber2: 3
15:23:14.974 [main] INFO com.insuk.reactor.ch9.Sinks_SinksMany_multicast_replay -- # Subscriber2: 4
 */
/**
 * replay() 메서드를 호출하면 리턴 값으로 MulticastReplaySpec 을 리턴한다.
 * MulticastReplaySpec 에는 emit 된 데이터를 다시 replay 헤서 구독 전에 이미 emit 된 데이터라도 Subscriber 가 전달받을 수 있게 한다.
 *
 * limit() 메서드는 emit된 데이터 중에서 파라미터로 입력한 개수만큼 가장 나중에 emit된 데이터부터 Subscriber에게 전달하는 기능을 합니다.
 * 즉, emit된 데이터 중에서 2개만 뒤로 돌려서(replay) 전달하겠다는 의미입니다.
 *
 * 실행 결과를 보면, 첫 번째 Subscriber 의 입장에서는 구독 시점에 이미 세 개의 데이터가 emit 되었기 때문에 마지막 2개를 뒤로 되돌린 숫자가 2이므로 2부터 전달됩니다.
 * 하지만, 두 번째 Subscriber의 경우, 구독 전에 숫자 4의 데이터가 한 번 더 emit 되었기 때문에 두 번째 Subscriber의 구독 시점에 마지막 2개를 뒤로 돌린 숫자가 3이므로 3부터 전달됩니다.
 */