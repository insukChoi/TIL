package com.example.kotlincoroutines.ch11

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val a = coroutineScope {
        delay(1000)
        10
    }
    println("a is calculated")
    val b = coroutineScope {
        delay(1000)
        20
    }
    println(a)
    println(b)
}
// (1초 후)
// a is calculated
// (1초 후)
// 10
// 20

// coroutineScope 함수는 새로운 코루틴을 생성하지만 새로운 코루틴이 끝날 때까지
// coroutineScope 를 호출한 코루틴을 중단하기 때문에 호출한 코루틴이 작업을 동시에 시작하지는 않습니다.