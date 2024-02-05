package com.example.kotlincoroutines.ch15.runTest

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class RunTest_3_BackgroundScope  {
    @Test
    fun `should increment counter`() = runTest {
        var i = 0
        // 백그라운드 스코프 또한 가상 시간을 지원하지만, runTest 가 스코프가 종료될 때까지 기다리지 않습니다.
        backgroundScope.launch {
            while (true) {
                delay(1000)
                i++
            }
        }
        delay(1001)
        assertEquals(1, i)
        delay(1000)
        assertEquals(2, i)
    }
}