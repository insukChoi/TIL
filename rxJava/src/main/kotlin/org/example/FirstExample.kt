package org.example

import io.reactivex.Observable

fun main(args: Array<String>) {
    FirstExample().emit()
}

class FirstExample {
    fun emit() {
        Observable.just("Hello", "RxJava 2 !!")
            .subscribe(::println)
    }
}