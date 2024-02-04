package com.example.kotlincoroutines.ch14

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

val mutex = Mutex()

var counter2 = 0

fun main() = runBlocking {
    massiveRun {
        mutex.withLock {
            counter2++
        }
    }

    println(counter2)
}

/**
 * synchronized 블록과 달리 뮤텍스가 가지는 중요한 이점은 스레드를 블로킹하는 대신 코루틴을 중단시킴
 * 좀더 안전하고 가벼운 방식
 */