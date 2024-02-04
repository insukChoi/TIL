package com.example.kotlincoroutines.ch14

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

suspend fun main() {
    val mutex = Mutex()
    println("Started")
    mutex.withLock {
        mutex.withLock {
            println("Will never be printed")
        }
    }
}

/**
 * 뮤텍스를 사용할 때 맞닥뜨리는 위험한 경우는 코루틴이 락을 두 번 통과할 수 없다는 것입니다.
 * (열쇠가 문 안쪽에 있으면 같은 열쇠를 필요로 하는 또 다른 문을 통과할 수 없는 것과 비슷)
 */