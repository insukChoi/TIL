package com.insuk.reactor.ch14.error;

import com.insuk.reactor.sample.Book;
import com.insuk.reactor.sample.SampleData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * onErrorResume() Operator는 에러 이벤트가 발생했을 때,
 * 에러 이벤트를 Downstream으로 전파하지 않고 대체 Publisher를 리턴합니다.
 */
public class OnErrorResume {
    private static final Logger log = LoggerFactory.getLogger(OnErrorResume.class);

    public static void main(String[] args) {
        final String keyword = "DDD";
        getBooksFromCache(keyword)
                .onErrorResume(error -> getBooksFromDatabase(keyword))
                .subscribe(data -> log.info("# onNext: {}", data.getBookName()),
                        error -> log.error("# onError", error)
                );
    }

    public static Flux<Book> getBooksFromCache(final String keyword) {
        return Flux.fromIterable(SampleData.books)
                .filter(book -> book.getBookName().contains(keyword))
                .switchIfEmpty(Flux.error(new NoSuchBookException("No such Book")));
    }

    public static Flux<Book> getBooksFromDatabase(final String keyword) {
        List<Book> books = new ArrayList<>(SampleData.books);
        books.add(new Book("DDD: Domain Driven Design", "Joy", "ddd-man", 35000, 200));
        return Flux.fromIterable(books)
                .filter(book -> book.getBookName().contains(keyword))
                .switchIfEmpty(Flux.error(new NoSuchBookException("No such Book")));
    }

    private static class NoSuchBookException extends RuntimeException {
        NoSuchBookException(final String message) {
            super(message);
        }
    }
}
/*
21:33:31.535 [main] INFO com.insuk.reactor.ch14.error.OnErrorResume -- # onNext: DDD: Domain Driven Design
 */