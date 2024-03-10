package com.example.kotlincoroutines.ch24

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    val flow = flowOf("A", "B", "C")
        .onEach { delay(1000) }

    val sharedFlow = flow.shareIn(
        scope = this,
        started = SharingStarted.Eagerly
    )

    delay(500)
    launch {
        sharedFlow.collect { println("#1 $it") }
    }

    delay(1000)
    launch {
        sharedFlow.collect { println("#2 $it") }
    }

    delay(1000)
    launch {
        sharedFlow.collect { println("#3 $it") }
    }
}
// (1초 후)
// #1 A
// (1초 후)
// #1 B
// #2 B
// (1초 후)
// #2 C
// #1 C
// #3 C
