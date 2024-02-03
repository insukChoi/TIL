package com.example.kotlincoroutines.ch12

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main(): Unit = coroutineScope {
    launch {
        printCoroutinesTime(Dispatchers.IO)
        // Dispatchers.IO took: 2074
    }
    launch {
        val dispatcher = Dispatchers.IO.limitedParallelism(100)
        printCoroutinesTime(dispatcher)
        // LimitedDispatcher@XXX took: 1082
    }
}

suspend fun printCoroutinesTime(
    dispatcher: CoroutineDispatcher
) {
    val test = measureTimeMillis {
        coroutineScope {
            repeat(100) {
                launch(dispatcher) {
                    Thread.sleep(1000)
                }
            }
        }
    }
    println("$dispatcher took: $test")
}

// limitedParallelism 로 Dispatcher 를 새로 정의한다면, 다른 Dispatcher들과 독립적인 디스패처를 만드는 것 (독립적인 쓰레드 제한의 디스패처)
// 하지만 모든 디스패처는 스레드가 무제한인 스레드 풀을 공유한다.