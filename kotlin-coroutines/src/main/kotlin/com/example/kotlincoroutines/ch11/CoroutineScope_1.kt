package com.example.kotlincoroutines.ch11

import kotlinx.coroutines.*

suspend fun longTask() = coroutineScope {
    launch {
        delay(1000)
        val name = coroutineContext[CoroutineName]?.name
        println("[$name] Finished task 1")
    }
    launch {
        delay(2000)
        val name = coroutineContext[CoroutineName]?.name
        println("[$name] Finished task 2")
    }
}

fun main() = runBlocking(CoroutineName("Parent")) {
    println("Before")
    longTask()
    println("After")
}

// Before
// (1초 후)
// [Parent] Finished task 1
// (1초 후)
// [Parent] Finished task 2
// After

// coroutineScope 는 모든 자식이 끝날 때까지 종료되지 않으므로 'After' 가 가장 마지막에 출력된다.