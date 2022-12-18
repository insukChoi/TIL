package org.example.ch1

import io.reactivex.Flowable

fun main(args: Array<String>) {
    val flowable = Flowable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        .filter { it % 2 == 0 }
        .map { it * 100 }

    flowable.subscribe { println("data = $it") }
}