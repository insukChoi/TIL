package com.insuk.reactor.ch14.error;

import com.insuk.reactor.sample.Book;
import com.insuk.reactor.sample.SampleData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * onErrorReturn() Operator는 에러 이벤트가 발생했을 때, 에러 이벤트를 Downstream으로 전파하지 않고 대체 값을 emit합니다.
 * Java에서 예외가 발생했을 때, try ~ catch 문의 catch 블록세어 예외에 해당하는 대체 값을 리턴하는 방식과 유사하다고 생각하면 된다.
 */
public class OnErrorReturn {
    private static final Logger log = LoggerFactory.getLogger(OnErrorReturn.class);

    public static void main(String[] args) {
        getBooks()
                .map(book -> book.getPenName().toUpperCase())
                .onErrorReturn("No pen name")
                .subscribe(log::info);
    }

    public static Flux<Book> getBooks() {
        return Flux.fromIterable(SampleData.books);
    }
}
/*
21:24:37.690 [main] INFO com.insuk.reactor.ch14.error.OnErrorReturn -- TOM-BOY
21:24:37.691 [main] INFO com.insuk.reactor.ch14.error.OnErrorReturn -- GRACE-GIRL
21:24:37.691 [main] INFO com.insuk.reactor.ch14.error.OnErrorReturn -- DAVID-BOY
21:24:37.691 [main] INFO com.insuk.reactor.ch14.error.OnErrorReturn -- TOM-BOY
21:24:37.691 [main] INFO com.insuk.reactor.ch14.error.OnErrorReturn -- KEVIN-BOY
21:24:37.691 [main] INFO com.insuk.reactor.ch14.error.OnErrorReturn -- TOM-BOY
21:24:37.691 [main] INFO com.insuk.reactor.ch14.error.OnErrorReturn -- KEVIN-BOY
21:24:37.691 [main] INFO com.insuk.reactor.ch14.error.OnErrorReturn -- GRACE-GIRL
21:24:37.694 [main] INFO com.insuk.reactor.ch14.error.OnErrorReturn -- No pen name
 */