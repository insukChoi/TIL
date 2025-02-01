package com.insuk.reactor.ch14.transfer;

import com.insuk.reactor.sample.SampleData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * CollectList() Operator는 Flux에서 emit된 데이터를 모아서 List로 변환한 후,
 * 변환된 List를 emit하는 Mono를 반환합니다.
 */
public class CollectList {
    private static final Logger log = LoggerFactory.getLogger(CollectList.class);

    public static void main(String[] args) {
        Flux.just("...", "---", "...")
                .map(CollectList::transformMorseCode)
                .collectList()
                .subscribe(list -> log.info(String.join("", list)));
    }

    public static String transformMorseCode(String morseCode) {
        return SampleData.morseCodeMap.get(morseCode);
    }
}
/*
19:19:38.739 [main] INFO com.insuk.reactor.ch14.transfer.CollectList -- sos
 */