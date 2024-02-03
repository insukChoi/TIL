package com.example.kotlincoroutines.ch12

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random

suspend fun main() = coroutineScope {
    repeat(1000) {
        launch { // 또는 launch(Dispatcher.Default)
            // 바쁘게 만들기 위해 실행합니다.
            List(1000) { Random.nextLong() }.maxOrNull()

            val threadName = Thread.currentThread().name
            println("Running on thread: $threadName")
        }
    }
}

// runBlocking 은 디스패처가 설정되어 있지 않으면 자신만의 디스패처를 사용하기 때문에 Dispatchers.Default 가 자동으로 선책되지 않습니다.
// 위 예제에서 coroutineScope 대신에 runBlocking 을 사용하면 모든 코루틴은 'main' 에서 실행됩니다.