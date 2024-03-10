package com.example.kotlincoroutines.ch23

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

private fun flowFrom(elem: String) = flowOf(1, 2, 3)
    .onEach { delay(1000) }
    .map { "${it}_${elem}" }

/**
 * flatMapLatest 는 새로운 플로우가 나타나면 이전에 처리하던 플로우를 잊어버립니다.
 * "A", "B", "C" 사이에 지연이 없다면 "1_C", "2_C", "3_C" 만 보게 될 것입니다.
 */
@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main() {
    flowOf("A" , "B", "C")
        .flatMapLatest { flowFrom(it) }
        .collect { println(it) }
}
// (1초 후)
// 1_C
// (1초 후)
// 2_C
// (1초 후)
// 3_C