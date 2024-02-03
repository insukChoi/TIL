package com.example.kotlincoroutines.ch11

import kotlinx.coroutines.*

suspend fun main(): Unit = coroutineScope {
    launch {  // 1
        launch { // 2, 부모에 의해 취소됩니다.
            delay(2000)
            println("Will not be printed")
        }
        withTimeout(1000) { // 이 코루틴이 launch를 취소합니다.
            delay(1500)
        }
    }

    launch { // 3
        delay(2000)
        println("Done")
    }
}

// (2초 후)
// Done