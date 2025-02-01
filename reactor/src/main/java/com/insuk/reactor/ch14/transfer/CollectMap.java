package com.insuk.reactor.ch14.transfer;

import com.insuk.reactor.sample.SampleData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * collecMap() Operator는 Flux에서 emit된 데이터를 기반으로 key와 value를 생성하여
 * Map의 Element로 추가한 후, 최종적으로 Map을 emit하는 Mono를 반환합니다.
 */
public class CollectMap {
    private static final Logger log = LoggerFactory.getLogger(CollectMap.class);

    public static void main(String[] args) {
        Flux.range(0, 26)
                .collectMap(
                        key -> SampleData.morseCodes[key],
                        value -> transformLetter(value)
                ).subscribe(map -> log.info("# onNext: {}", map));
    }

    private static String transformLetter(int value) {
        return Character.toString((char) ('a' + value));
    }
}
/*
19:25:11.388 [main] INFO com.insuk.reactor.ch14.transfer.CollectMap -- # onNext: {..=i, .---=j, --.-=q, ---=o, --.=g, .--=w, .-.=r, -.--=y, ....=h, -.-.=c, --=m, -.=n, .-..=l, ...-=v, -.-=k, -..=d, -=t, ..-=u, .=e, ...=s, -...=b, ..-.=f, .--.=p, -..-=x, --..=z, .-=a}
 */
