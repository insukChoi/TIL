package com.insuk.reactor.ch14.error;

import com.insuk.reactor.sample.Book;
import com.insuk.reactor.sample.SampleData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Collectors;

public class Retry_2 {
    private static final Logger log = LoggerFactory.getLogger(Retry_2.class);

    public static void main(String[] args) throws InterruptedException {
        getBooks()
                .collect(Collectors.toSet())
                .subscribe(bookSet -> bookSet.forEach(
                        book -> log.info("book name: {}, price: {}", book.getBookName(), book.getPrice())
                ));

        Thread.sleep(12000);
    }

    private static Flux<Book> getBooks() {
        final int[] count = {0};
        return Flux.fromIterable(SampleData.books)
                .delayElements(Duration.ofMillis(500))
                .map(book -> {
                    try {
                        count[0]++;
                        if (count[0] == 3) {
                            Thread.sleep(2000);
                        }
                    } catch (InterruptedException e) {}

                    return book;
                })
                .timeout(Duration.ofSeconds(2)) // 2초 동안 emit되는 데이터가 없다면 1회 재시도하여 도서 목록을 다시 조회할 수 있습니다.
                .retry(1)
                .doOnNext(book -> log.info("# getBooks > doOnNext: {}, price: {}", book.getBookName(), book.getPrice()));
    }
}
/*
22:01:32.381 [parallel-1] INFO com.insuk.reactor.ch14.error.Retry_2 -- # getBooks > doOnNext: Advance Java, price: 25000
22:01:32.891 [parallel-4] INFO com.insuk.reactor.ch14.error.Retry_2 -- # getBooks > doOnNext: Advance Python, price: 22000
22:01:35.401 [parallel-7] INFO com.insuk.reactor.ch14.error.Retry_2 -- # getBooks > doOnNext: Advance Java, price: 25000
22:01:35.905 [parallel-10] INFO com.insuk.reactor.ch14.error.Retry_2 -- # getBooks > doOnNext: Advance Python, price: 22000
22:01:36.410 [parallel-2] INFO com.insuk.reactor.ch14.error.Retry_2 -- # getBooks > doOnNext: Advance Reactor, price: 35000
22:01:36.916 [parallel-4] INFO com.insuk.reactor.ch14.error.Retry_2 -- # getBooks > doOnNext: Getting started Java, price: 32000
22:01:37.421 [parallel-6] INFO com.insuk.reactor.ch14.error.Retry_2 -- # getBooks > doOnNext: Advance Kotlin, price: 32000
22:01:37.927 [parallel-8] INFO com.insuk.reactor.ch14.error.Retry_2 -- # getBooks > doOnNext: Advance Javascript, price: 32000
22:01:38.432 [parallel-10] INFO com.insuk.reactor.ch14.error.Retry_2 -- # getBooks > doOnNext: Getting started Kotlin, price: 32000
22:01:38.939 [parallel-2] INFO com.insuk.reactor.ch14.error.Retry_2 -- # getBooks > doOnNext: Getting started Python, price: 32000
22:01:39.444 [parallel-4] INFO com.insuk.reactor.ch14.error.Retry_2 -- # getBooks > doOnNext: Getting started Reactor, price: 32000
22:01:39.950 [parallel-6] INFO com.insuk.reactor.ch14.error.Retry_2 -- # getBooks > doOnNext: Getting started Javascript, price: 32000
22:01:39.954 [parallel-6] INFO com.insuk.reactor.ch14.error.Retry_2 -- book name: Advance Java, price: 25000
22:01:39.954 [parallel-6] INFO com.insuk.reactor.ch14.error.Retry_2 -- book name: Advance Reactor, price: 35000
22:01:39.954 [parallel-6] INFO com.insuk.reactor.ch14.error.Retry_2 -- book name: Advance Javascript, price: 32000
22:01:39.954 [parallel-6] INFO com.insuk.reactor.ch14.error.Retry_2 -- book name: Getting started Kotlin, price: 32000
22:01:39.954 [parallel-6] INFO com.insuk.reactor.ch14.error.Retry_2 -- book name: Advance Kotlin, price: 32000
22:01:39.954 [parallel-6] INFO com.insuk.reactor.ch14.error.Retry_2 -- book name: Getting started Reactor, price: 32000
22:01:39.954 [parallel-6] INFO com.insuk.reactor.ch14.error.Retry_2 -- book name: Advance Python, price: 22000
22:01:39.954 [parallel-6] INFO com.insuk.reactor.ch14.error.Retry_2 -- book name: Getting started Java, price: 32000
22:01:39.954 [parallel-6] INFO com.insuk.reactor.ch14.error.Retry_2 -- book name: Getting started Python, price: 32000
22:01:39.954 [parallel-6] INFO com.insuk.reactor.ch14.error.Retry_2 -- book name: Getting started Javascript, price: 32000
 */