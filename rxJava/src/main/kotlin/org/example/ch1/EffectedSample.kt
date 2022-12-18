package org.example.ch1

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

enum class State {
    ADD, MULTIPLY
}

fun main(args: Array<String>) {
    // 계산 방법을 덧셈으로 설정
    var calcMethod = State.ADD

    val flowable = Flowable.interval(300L, TimeUnit.MILLISECONDS)
        .take(7)
        .scan { sum, data ->
            if (calcMethod == State.ADD) {
                sum + data
            } else {
                sum * data
            }
        }

    // 구독하고 데이터 출력
    flowable.subscribe { println("data = $it") }

    // 잠시 기다렸다가 계산 방법을 곱셈으로 변경
    Thread.sleep(1000)
    println("계산 방법 변경")
    calcMethod = State.MULTIPLY

    // 잠시 기다린다
    Thread.sleep(2000)
}