package com.insuk.reactor.ch14.division;

import com.insuk.reactor.sample.SampleData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * groupBy(keyMapper) Operator는 emit되는 데이터를 keyMapper로 생성한 key를 기준으로 그룹화한 GroupedFlux를 리턴하며,
 * 이 GroupedFlux를 통해서 그룹별로 작업을 수행할 수 있습니다.
 */
public class GroupBy {
    private static final Logger log = LoggerFactory.getLogger(GroupBy.class);

    public static void main(String[] args) {
        Flux.fromIterable(SampleData.books)
                .groupBy(book -> book.getAuthorName())
                .flatMap(groupedFlux ->
                        groupedFlux.map(book -> book.getBookName() + "(" + book.getAuthorName() + ")").collectList()
                ).subscribe(bookByAuthor -> log.info("# book by author: {}", bookByAuthor));
    }
}
/*
13:43:34.241 [main] INFO com.insuk.reactor.ch14.division.GroupBy -- # book by author: [Advance Kotlin(Kevin), Getting started Kotlin(Kevin)]
13:43:34.242 [main] INFO com.insuk.reactor.ch14.division.GroupBy -- # book by author: [Advance Javascript(Mike), Getting started Javascript(Mike)]
13:43:34.242 [main] INFO com.insuk.reactor.ch14.division.GroupBy -- # book by author: [Advance Java(Tom), Getting started Java(Tom)]
13:43:34.243 [main] INFO com.insuk.reactor.ch14.division.GroupBy -- # book by author: [Advance Python(Grace), Getting started Python(Grace)]
13:43:34.243 [main] INFO com.insuk.reactor.ch14.division.GroupBy -- # book by author: [Advance Reactor(Smith), Getting started Reactor(Smith)]
 */