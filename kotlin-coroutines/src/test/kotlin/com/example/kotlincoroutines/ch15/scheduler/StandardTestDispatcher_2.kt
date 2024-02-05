package com.example.kotlincoroutines.ch15.scheduler

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler

// 기본적으로 StandardTestDispatcher 는 TestCoroutineScheduler 를 만들기 때문에 명시적으로 만들지 않아도 됩니다.
// 디스패처의 scheduler 프로퍼티로 해당 스케줄러에 접근할 수 있다.

@OptIn(ExperimentalCoroutinesApi::class)
fun main() {
    val testDispatcher = StandardTestDispatcher()

    CoroutineScope(testDispatcher).launch {
        println("Some work 1")
        delay(1000)
        println("Some work 2")
        delay(1000)
        println("Coroutine done")
    }

    println("[${testDispatcher.scheduler.currentTime}] Before")
    testDispatcher.scheduler.advanceUntilIdle()
    println("[${testDispatcher.scheduler.currentTime}] After")
}