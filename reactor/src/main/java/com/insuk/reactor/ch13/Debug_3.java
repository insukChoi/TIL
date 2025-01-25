package com.insuk.reactor.ch13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Debug_3 {
    private static final Logger log = LoggerFactory.getLogger(Debug_3.class);

    public static void main(String[] args) {
        Flux<Integer> source = Flux.just(2, 4, 6, 8);
        Flux<Integer> other = Flux.just(1, 2, 3, 0);

        Flux<Integer> multiplySource = multiply(source, other).checkpoint();
        Flux<Integer> plusSource = plus(multiplySource).checkpoint();

        plusSource.subscribe(
                data -> log.info("# onNext: {}", data),
                error -> log.error("# onError", error)
        );
    }

    private static Flux<Integer> multiply(Flux<Integer> source, Flux<Integer> other) {
        return source.zipWith(other, (i1, i2) -> i1 / i2);
    }

    private static Flux<Integer> plus(Flux<Integer> source) {
        return source.map(num -> num + 2);
    }
}
