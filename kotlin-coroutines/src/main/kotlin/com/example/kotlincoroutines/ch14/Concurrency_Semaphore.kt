package com.example.kotlincoroutines.ch14

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit

suspend fun main() = coroutineScope {
    val semaphore = Semaphore(2)

    repeat(5) {
        launch {
            semaphore.withPermit {
                delay(1000)
                print(it)
            }
        }
    }
}

// 01
// (1초 후)
// 23
// (1초 후)
// 4

/**
 * 세마포어는 공유 상태로 인해 생기는 문제를 해결할 수는 없지만, 동시 요청을 처리하는 수를 제한할 때 사용할 수 있어
 * '처리율 제한 장치(rate limiter)' 를 구현할 때 도움이 됩니다.
 */