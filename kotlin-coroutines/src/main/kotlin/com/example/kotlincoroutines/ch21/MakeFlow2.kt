package com.example.kotlincoroutines.ch21

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow

/**
 * 플로우는 (RxJava 의 Single 처럼) 시간상 지연되는 하나의 값을 나타낼 때 자주 사용됩니다.
 */
suspend fun main() {
    val function = suspend {
        // 중단 함수를 람다식으로 만든 것
        delay(1_000)
        "UserName"
    }

    function.asFlow()
        .collect {
            println(it)
        }
}
// (1초 후)
// UserName