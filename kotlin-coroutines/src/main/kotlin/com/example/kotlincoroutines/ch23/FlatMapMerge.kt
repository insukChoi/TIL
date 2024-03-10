package com.example.kotlincoroutines.ch23

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

private fun flowFrom(elem: String) = flowOf(1, 2, 3)
    .onEach { delay(1000) }
    .map { "${it}_${elem}" }

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main() {
    flowOf("A" , "B", "C")
        .flatMapMerge { flowFrom(it) }
        .collect { println(it) }
}
// (1초 후)
// 1_B
// 1_C
// 1_A
// (1초 후)
// 2_C
// 2_A
// 2_B
// (1초 후)
// 3_A
// 3_B
// 3_C