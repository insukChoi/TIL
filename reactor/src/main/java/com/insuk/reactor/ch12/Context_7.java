package com.insuk.reactor.ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Context_7 {
    public static final String HEADER_AUTH_TOKEN = "authToken";
    private static final Logger log = LoggerFactory.getLogger(Context_7.class);

    public static void main(String[] args) {
        Mono<String> mono = postBook(
                Mono.just(
                        new Book("abcd-1111-3533-2909", "Reactor's Bible", "Kevin")
                ).contextWrite(Context.of(HEADER_AUTH_TOKEN, "eyJhGciOi"))
        );

        mono.subscribe(data -> log.info("# onNext: {}", data));
    }

    private static Mono<String> postBook(Mono<Book> book) {
        return Mono.zip(book, Mono.deferContextual(ctx -> Mono.just(ctx.get(HEADER_AUTH_TOKEN))))
                .flatMap(tuple -> {
                    String response = "POST the book(" + tuple.getT1().getBookName() + "," + tuple.getT1().getAuthor() + ") with token: " + tuple.getT2();
                    return Mono.just(response);
                });
    }

    static class Book {
        private String isbn;
        private String bookName;
        private String author;

        public Book(final String isbn, final String bookName, final String author) {
            this.isbn = isbn;
            this.bookName = bookName;
            this.author = author;
        }

        public String getIsbn() {
            return isbn;
        }

        public void setIsbn(final String isbn) {
            this.isbn = isbn;
        }

        public String getBookName() {
            return bookName;
        }

        public void setBookName(final String bookName) {
            this.bookName = bookName;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(final String author) {
            this.author = author;
        }
    }
}
/**
 * 예제 코드의 핵심은 16라인에서 Context에 저장한 인증 토큰을 두 개의 Mono를 합치는 과정(23라인)에서 다시 Context로부터 읽어 와서 사용한다는 것입니다.
 * Mono가 어떤 과정을 거치든 상관없이 가장 마지막에 리턴된 Mono를 구독하기 직전에 contextWrite()으로 데이터를 저장하기 떼문에 Operator 체인의 위쪽으로 전파되고,
 * Operator 체인 어느 위치에서든 Context에 접근할 수 있는 것입니다.
 *
 * Context는 인증 정보 같은 직교성(독립성)을 가지는 정보를 전송하는데 적합하다.
 */