package com.example.kotlincoroutines.ch9

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    val job = launch {
        delay(1000)
    }
    // 'Completed', 'Cancelled' 와 같은 마지막 상태에 도달했을 때 호출될 핸들러 지정
    job.invokeOnCompletion { exception: Throwable? ->
        println("Finished")
    }
    delay(400)
    job.cancelAndJoin()
}