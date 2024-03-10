package com.example.kotlincoroutines.ch24

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

/**
 * SharingStarted.Eagerly 은 즉시 값을 감지하기 시작하고 플로우로 값을 전송합니다.
 * replay 값에 제한이 있고 감지를 시작하기 전에 값이 나오면 일부를 유실할 수도 있습니다.
 * (만약 리플레이 인자가 0이라면 먼저 들어온 값이 전부 유실됩니다.)
 */
suspend fun main(): Unit = coroutineScope {
    val flow = flowOf("A", "B", "C")

    val sharedFlow = flow.shareIn(
        scope = this,
        started = SharingStarted.Eagerly
    )

    delay(100)
    launch {
        sharedFlow.collect { println("#1 $it") }
    }
    println("Done")
}
// (0.1초 후)
// Done