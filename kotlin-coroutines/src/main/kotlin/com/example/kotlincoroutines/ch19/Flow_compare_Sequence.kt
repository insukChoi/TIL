package com.example.kotlincoroutines.ch19

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * 플로우는 코루틴을 사용해야 하는 데이터 스트림으로 사용되어야 합니다.
 */
fun getFlow(): Flow<String> = flow {
    repeat(3) {
        delay(1000)
        emit("User$it")
    }
}

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
suspend fun main() {
    withContext(newSingleThreadContext("main")) {
        launch {
            repeat(3) {
                delay(100)
                println("Processing on coroutine")
            }
        }

        val list = getFlow()
        list.collect { println(it) }
    }
}
// (0.1초 후)
// Processing on coroutine
// (0.1초 후)
// Processing on coroutine
// (0.1초 후)
// Processing on coroutine
// (1 - 3 * 0.1 = 0.7초 후)
// User0
// (1초 후)
// User1
// (1초 후)
// User2