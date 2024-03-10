package com.example.kotlincoroutines.ch22

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach

suspend fun main() {
    flowOf(1, 2, 3, 4)
        .onEach { print(it) }
        .collect() // 1234

    // onEach 람다식은 중단 함수이며, 원소는 순서대로 처리됩니다.
    // 따라서 OnEach 에 delay 를 넣으면 각각의 값이 흐를 때마다 지연되게 됩니다.
    flowOf(1, 2)
        .onEach { delay(1000) }
        .collect {
            print(it)
        }
    // (1초 후)
    // 1
    // (1초 후)
    // 2
}