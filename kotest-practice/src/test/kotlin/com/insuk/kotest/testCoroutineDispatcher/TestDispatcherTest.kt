package com.insuk.kotest.testCoroutineDispatcher

import io.kotest.core.spec.style.FunSpec
import io.kotest.core.test.testCoroutineScheduler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.days

@OptIn(ExperimentalStdlibApi::class, ExperimentalCoroutinesApi::class)
class TestDispatcherTest : FunSpec() {
    init {
        test("advance time").config(coroutineTestScope = true) {
            val duration = 1.days

            // 일반적으로 1일 동안 휴면하는 코루틴을 시작합니다.
            launch {
                delay(duration.inWholeMilliseconds)
            }

            // 시계를 켜면 위 코루틴의 지연이 즉시 완료됩니다.
            testCoroutineScheduler.advanceTimeBy(duration.inWholeMilliseconds)
            val currentTime = testCoroutineScheduler.currentTime
            println("currentTime = $currentTime")
        }
    }
}