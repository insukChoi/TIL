package com.example.kotlincoroutines.ch14

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.system.measureTimeMillis

class MessageRepository {
    private val messages = mutableListOf<String>()
    private val mutex = Mutex()

    suspend fun add(message: String) = mutex.withLock {
        delay(1000) // 네트워크 호출이라고 가정
        messages.add(message)
    }
}

suspend fun main() {
    val repo = MessageRepository()

    val timeMillis = measureTimeMillis {
        coroutineScope {
            repeat(5) {
                launch {
                    repo.add("Message$it")
                }
            }
        }
    }

    println(timeMillis) // 5052
}

/**
 * 뮤텍스가 가진 두 번째 문제점은 코루틴이 중단되었을 때 뮤텍스를 풀 수 없다는 점입니다.
 * 코드를 보면 delay 중에 뮤텍스가 잠겨있어 5초가 걸리는 걸 확인할 수 있습니다.
 *
 * 즉, 뮤텍스를 사용하기로 했다면 락을 두 번 걸지 않고 중단 함수를 호출하지 않도록 신경써야 함.
 */