package com.example.kotlincoroutines.ch23

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

private fun flowFrom(elem: String) = flowOf(1, 2, 3)
    .onEach { delay(1000) }
    .map { "${it}_${elem}" }

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main() {
    flowOf("A" , "B", "C")
        .flatMapConcat { flowFrom(it) }
        .collect { println(it) }
}
// (1초 후)
// 1_A
// (1초 후)
// 2_A
// (1초 후)
// 3_A
// (1초 후)
// 1_B
// (1초 후)
// 2_B
// (1초 후)
// 3_B
// (1초 후)
// 1_C
// (1초 후)
// 2_C
// (1초 후)
// 3_C