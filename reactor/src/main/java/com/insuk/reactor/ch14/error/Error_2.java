package com.insuk.reactor.ch14.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.zip.DataFormatException;

public class Error_2 {
    private static final Logger log = LoggerFactory.getLogger(Error_2.class);

    public static void main(String[] args) {
        Flux.just('a', 'b', 'c', '3', 'd')
                .flatMap(letter -> {
                    try {
                        return convert(letter);
                    } catch (DataFormatException e) {
                        return Flux.error(e);
                    }
                }).subscribe(
                        data -> log.info("# onNext: {}", data),
                        error -> log.error("# onError: ", error)
                );

    }

    private static Mono<String> convert(char ch) throws DataFormatException {
        if (!Character.isAlphabetic(ch)) {
            throw new DataFormatException("Not Alphabetic");
        }
        return Mono.just("Converted to " + Character.toUpperCase(ch));
    }
}
/*
21:08:42.003 [main] INFO com.insuk.reactor.ch14.error.Error_2 -- # onNext: Converted to A
21:08:42.005 [main] INFO com.insuk.reactor.ch14.error.Error_2 -- # onNext: Converted to B
21:08:42.005 [main] INFO com.insuk.reactor.ch14.error.Error_2 -- # onNext: Converted to C
21:08:42.007 [main] ERROR com.insuk.reactor.ch14.error.Error_2 -- # onError:
java.util.zip.DataFormatException: Not Alphabetic
	at com.insuk.reactor.ch14.error.Error_2.convert(Error_2.java:30)
	at com.insuk.reactor.ch14.error.Error_2.lambda$main$0(Error_2.java:17)
	at reactor.core.publisher.FluxFlatMap$FlatMapMain.onNext(FluxFlatMap.java:388)
	at reactor.core.publisher.FluxArray$ArraySubscription.slowPath(FluxArray.java:126)
	at reactor.core.publisher.FluxArray$ArraySubscription.request(FluxArray.java:99)
	at reactor.core.publisher.FluxFlatMap$FlatMapMain.onSubscribe(FluxFlatMap.java:373)
	at reactor.core.publisher.FluxArray.subscribe(FluxArray.java:53)
	at reactor.core.publisher.FluxArray.subscribe(FluxArray.java:59)
	at reactor.core.publisher.Flux.subscribe(Flux.java:8891)
	at reactor.core.publisher.Flux.subscribeWith(Flux.java:9012)
	at reactor.core.publisher.Flux.subscribe(Flux.java:8856)
	at reactor.core.publisher.Flux.subscribe(Flux.java:8780)
	at reactor.core.publisher.Flux.subscribe(Flux.java:8750)
	at com.insuk.reactor.ch14.error.Error_2.main(Error_2.java:21)
 */