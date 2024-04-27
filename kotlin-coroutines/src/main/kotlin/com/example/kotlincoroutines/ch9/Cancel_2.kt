package com.example.kotlincoroutines.ch9

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.cancellation.CancellationException

suspend fun test() = coroutineScope {
    val job = launch {
        repeat(10) { i ->
            println("repeat: $i ...")
            try {
                delay(10L) // cancel 일때 suspend 함수는 예외를 던지게끔 바뀐다.
            } catch (e: CancellationException) {
                println("catch $e")
            }
        }
    }
    val handel = job.invokeOnCompletion {
        println("invokeOnCompletion $it")
    }
    delay(55L)

    job.cancel()
    println("canceled $job")
}

fun main() {
    runBlocking {
        test()
        println("canceled main")
    }
}