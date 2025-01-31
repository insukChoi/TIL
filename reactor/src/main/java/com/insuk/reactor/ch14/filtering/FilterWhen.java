package com.insuk.reactor.ch14.filtering;

import com.insuk.reactor.sample.SampleData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

import java.util.Map;

import static com.insuk.reactor.sample.SampleData.getCovidVaccines;

/**
 * filterWhen() Operator는 내부에서 Inner Sequence를 통해 조건에 맞는 데이터인지를 비동기적으로 테스트 한 후,
 * 테스트 결과가 true라면 filterWhen()의 Upstream으로부터 전달받은 데이터를 Downstream으로 emit합니다.
 */
public class FilterWhen {
    private static final Logger log = LoggerFactory.getLogger(FilterWhen.class);

    public static void main(String[] args) throws InterruptedException {
        Map<SampleData.CovidVaccine, Tuple2<SampleData.CovidVaccine, Integer>> vaccineMap = getCovidVaccines();

        Flux.fromIterable(SampleData.coronaVaccineNames)
                .filterWhen(vaccine -> Mono
                        .just(vaccineMap.get(vaccine).getT2() >= 3_000_000)
                        .publishOn(Schedulers.parallel())
                ).subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(1000);
    }
}
/*
21:30:29.706 [parallel-2] INFO com.insuk.reactor.ch14.filtering.FilterWhen -- # onNext: AstraZeneca
21:30:29.708 [parallel-3] INFO com.insuk.reactor.ch14.filtering.FilterWhen -- # onNext: Moderna
 */