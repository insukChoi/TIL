package com.example.kotlincoroutines.ch15.scheduler

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.jupiter.api.Test

internal class UnconfinedTestDispatcher {
    @Test
    fun main() {
        CoroutineScope(StandardTestDispatcher()).launch {
            print("A")
            delay(1)
            print("B")
        }

        CoroutineScope(UnconfinedTestDispatcher()).launch {
            print("C")
            delay(1)
            print("D")
        }
    }
}
// C

/**
 * UnconfinedTestDispatcher 는 코루틴이 시작했을 때 첫 번째 지연이 일어나기 전까지의 모든 연산을 즉시 수행한다.
 * 반면에, StandardTestDispatcher 는 스케줄러를 사용하기 전까지 어떤 연산도 수행하지 않는다.
 */