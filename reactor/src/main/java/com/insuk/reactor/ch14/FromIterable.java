package com.insuk.reactor.ch14;

import com.insuk.reactor.sample.SampleData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class FromIterable {
    private static final Logger log = LoggerFactory.getLogger(FromIterable.class);

    public static void main(String[] args) {
        // fromIterable : 해당 리스트의 데이터를 순차적으로 emit 함
        Flux.fromIterable(SampleData.coins)
                .subscribe(coin -> {
                    log.info("coin 명: {}, 현재가: {}", coin.getT1(), coin.getT2());
                });

        // fromStream : Stream을 포함한 데이터를 emit하는 Flux를 생성합니다, (Java의 Steam 특성상 재사용할 수 없으며 cancel, error, complete 시에 자옹으로 닫히게 된다.)
        Flux.fromStream(() -> SampleData.coinNames.stream())
                .filter(coin -> coin.equals("BTC") || coin.equals("ETH"))
                .subscribe(data -> log.info("{}", data));
    }
}
/*
15:08:02.879 [main] INFO com.insuk.reactor.ch14.FromIterable -- coin 명: BTC, 현재가: 52000000
15:08:02.881 [main] INFO com.insuk.reactor.ch14.FromIterable -- coin 명: ETH, 현재가: 1720000
15:08:02.881 [main] INFO com.insuk.reactor.ch14.FromIterable -- coin 명: XRP, 현재가: 533
15:08:02.882 [main] INFO com.insuk.reactor.ch14.FromIterable -- coin 명: ICX, 현재가: 2080
15:08:02.882 [main] INFO com.insuk.reactor.ch14.FromIterable -- coin 명: EOS, 현재가: 4020
15:08:02.882 [main] INFO com.insuk.reactor.ch14.FromIterable -- coin 명: BCH, 현재가: 558000
 */

/*
15:12:47.056 [main] INFO com.insuk.reactor.ch14.FromIterable -- BTC
15:12:47.056 [main] INFO com.insuk.reactor.ch14.FromIterable -- ETH
 */