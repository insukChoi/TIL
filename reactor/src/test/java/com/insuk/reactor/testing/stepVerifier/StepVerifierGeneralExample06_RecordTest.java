package com.insuk.reactor.testing.stepVerifier;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Reactor Sequence를 테스트하다 보면 expectNext()로 emit된 데이터의 단순 기댓값만 평가하는 것이 아니라 조금 더 구체적인 조건으로
 * Assertion해야하는 경우가 많습니다.
 *
 * 이 경우, recordWith()를 사용할 수 있습니다.
 * recordWith()는 파라미터로 전달한 java의 컬렉션에 emit된 데이터를 추가(기록)하는 세션을 시작합니다.
 */
public class StepVerifierGeneralExample06_RecordTest {
    @Test
    public void getCityTest() {
        StepVerifier
                .create(StepVerifierGeneralExample06_RecordTest.getCapitalizedCountry(
                        Flux.just("korea", "england", "canada", "india")
                ))
                .expectSubscription()
                .recordWith(ArrayList::new) // emit된 데이터에 대한 기록을 시작합니다.
                .thenConsumeWhile(country -> !country.isEmpty()) // 파라미터로 전달한 Predicate과 일치하는 데이터는 다음 단계에서 소비할 수 있도록 합니다.
                // 컬렉션에 기록된 데이터를 소비합니다. 여기서는 모든 데이터의 첫 글자가 대문자인지 여부를 확인함으로써 getCapitalizedCountry() 메서드를 Assertion 합니다.
                .consumeRecordedWith(countries -> {
                    assertThat(
                            countries.stream().allMatch(country -> Character.isUpperCase(country.charAt(0)))
                    ).isTrue();
                })
                // 위 consumeRecordedWith 와 동일
//                .expectRecordedMatches(countries ->
//                    countries.stream().allMatch(country -> Character.isUpperCase(country.charAt(0)))
//                )
                .expectComplete()
                .verify();
    }

    public static Flux<String> getCapitalizedCountry(Flux<String> source) {
        return source
                .map(country -> country.substring(0, 1).toUpperCase() + country.substring(1));
    }
}
