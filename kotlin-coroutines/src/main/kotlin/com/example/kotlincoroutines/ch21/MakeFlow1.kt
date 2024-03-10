package com.example.kotlincoroutines.ch21

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf

suspend fun main() {
    // 원시값을 가지는 플로우
    flowOf(1,2,3,4,5)
        .collect {
            print(it) // 12345
        }

    // 아무값도 없는 플로우
    emptyFlow<Int>()
        .collect {
            print(it) // (아무것도 출력되지 않음)
        }

    // 컨버터
    listOf(1,2,3,4,5)
        .asFlow()
        .collect {
            print(it) // 12345
        }
}