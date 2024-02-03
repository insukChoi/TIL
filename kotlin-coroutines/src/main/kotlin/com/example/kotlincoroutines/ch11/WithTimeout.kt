package com.example.kotlincoroutines.ch11

import kotlinx.coroutines.*
import javax.print.attribute.standard.JobName

suspend fun test(): Int = withTimeout(1500) {
    delay(1000)
    println("Still thinking")
    delay(1000)
    println("Done!")
    42
}

suspend fun main(): Unit = coroutineScope {
    try {
        test()
    } catch (e: TimeoutCancellationException) {
        println("Cancelled")
    }
    delay(1000) // 'test' 함수가 취소되었기 때문에, 타임아웃 시간을 늘려도 아무런 도움이 되지 않음.
}
// (1초 후)
// Still thinking
// (0.5초 후)
// Cancelled