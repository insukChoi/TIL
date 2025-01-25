package com.insuk.reactor.testing.stepVerifier;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class StepVerifierGeneralExample05_ContextTest {

    @Test
    public void getSecretMessageTest() {
        Mono<String> source = Mono.just("hello");

        StepVerifier.create(
                StepVerifierGeneralExample05_ContextTest.getSecretMessage(source)
                        .contextWrite(context -> context.put("secretMessage", "Hello, Reactor"))
                        .contextWrite(context -> context.put("secretKey", "hello"))
        )
                .expectSubscription()
                .expectAccessibleContext()
                .hasKey("secretKey")
                .hasKey("secretMessage")
                .then()
                .expectNext("Hello, Reactor")
                .expectComplete()
                .verify();
    }

    public static Mono<String> getSecretMessage(Mono<String> keySource) {
        return keySource
                .zipWith(Mono.deferContextual(ctx -> Mono.just((String)ctx.get("secretKey"))))
                .filter(tp -> tp.getT1().equals(tp.getT2()))
                .transformDeferredContextual((mono, ctx) -> mono.map(notUse -> ctx.get("secretMessage")));
    }
}
