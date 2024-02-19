package com.example.kotlincoroutines.ch19

import kotlinx.coroutines.*

/**
 * Sequence 를 사용했기 때문에 forEach 가 블로킹 연산이 됩니다.
 * 따라서 같은 스레드에서 launch 로 시작된 코루틴이 대기하게 되며, 하나의 코루틴이 다른 코루틴을 블로킹하게 됩니다.
 *
 * 이런 상황에서 Sequence 대신에 Flow 를 사용해야합니다.
 */
fun getSequence(): Sequence<String> = sequence {
    repeat(3) {
        Thread.sleep(1000)
        // 여기에 delay(1000)이 있는 것과 같은 결과입니다.
        yield("User$it")
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main() {
    withContext(newSingleThreadContext("main")) {
        launch {
            repeat(3) {
                delay(100)
                println("Processing on coroutine")
            }
        }

        val list = getSequence()
        list.forEach { println(it) }
    }
}
// (1초 후)
// User0
// (1초 후)
// User1
// (1초 후)
// User2
// Processing on coroutine
// (0.1초 후)
// Processing on coroutine
// (0.1초 후)
// Processing on coroutine