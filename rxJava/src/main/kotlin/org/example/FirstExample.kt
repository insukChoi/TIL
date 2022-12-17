package org.example

import io.reactivex.Observable
import java.util.concurrent.Callable
import java.util.concurrent.Executors

fun main(args: Array<String>) {
    FirstExample().emit()
    FirstExample().disposableFunc()
    FirstExample().createFunc()
    FirstExample().callableFunc()
    FirstExample().futureFunc()
}

class FirstExample {
    fun emit() {
        Observable.just("Hello", "RxJava 2 !!")
            .subscribe(::println)
    }

    fun disposableFunc() {
        val source = Observable.just("RED", "GREEN", "YELLOW")
        val disposable = source.subscribe(
            { v -> println("onNext() : value : $v") },
            { err -> println("onError() : err : ${err.message}") },
            { println("onComplete()") }
        )

        println("isDisposed() : ${disposable.isDisposed}")
    }

    fun createFunc() {
        val source = Observable.create<Int> { emitter ->
            emitter.onNext(100)
            emitter.onNext(200)
            emitter.onNext(300)
            emitter.onComplete()
        }

        source.subscribe(::println)
    }

    fun callableFunc() {
        val source = Observable.fromCallable {
            Thread.sleep(1000)
            "Hello Callable"
        }

        source.subscribe(::println)
    }

    fun futureFunc() {
        val callable = Callable {
            Thread.sleep(1000)
            "Hello Future"
        }

        val future = Executors.newSingleThreadExecutor().submit(callable)

        val source = Observable.fromFuture(future)
        source.subscribe(::println)
    }
}