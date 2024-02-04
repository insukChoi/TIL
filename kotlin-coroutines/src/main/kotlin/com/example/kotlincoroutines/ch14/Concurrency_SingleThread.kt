package com.example.kotlincoroutines.ch14

import kotlinx.coroutines.*

/*
 * 싱글스레드 디스패처를 사용하는 것이 공유 상태와 관련된 대부분의 문제를 해결하는 가장 쉬운 방법
 */

val dispatcher = Dispatchers.IO.limitedParallelism(1) // 싱글스레드로 제한된 디스패처

var counter = 0

fun main() = runBlocking {
    massiveRun {
        withContext(dispatcher) {
            counter++
        }
    }

    println(counter)
}


suspend fun massiveRun(action: suspend () -> Unit) = coroutineScope {
    repeat(1000) {
        launch {
            repeat(1000) {
                action()
            }
        }
    }
}