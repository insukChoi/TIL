package com.example.kotlincoroutines.ch9

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    val job = launch {
        repeat(1_000) { i ->
            delay(200)
            println("Printing $i")
        }
    }

    delay(1100)
    job.cancel()
    job.join() // 코루틴이 취소가 마칠 때까지 중단되므로 경쟁상태가 발생하지 않는다.
    println("Cancelled successfully")
}