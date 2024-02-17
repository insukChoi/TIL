package com.example.kotlincoroutines.ch17

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

/**
 * 플로우는 콜드 테이터 소스이기 때문에 값을 필요할 때만 생상합니다.
 * 따라서 flow 는 빌더가 아니며 어떤 처리도 하지 않습니다.
 * flow 는 단지 (collect와 같은) 최종 연산이 호출될 때 원소가 어떻게 생성되어야 하는지 정의한 것에 불과합니다.
 * 그래서 flow 빌더는 CoroutineScope 가 필요하지 않습니다.
 *
 * 플로우의 각 최종 연산은 처음부터 데이터를 처리하기 시작합니다.
 */

private fun makeFlow() = flow {
    println("Flow stated...")
    for (i in 1..3) {
        delay(1000)
        emit(i)
    }
}

suspend fun main() = coroutineScope {
    val flow = makeFlow()

    delay(1000)
    println("Calling flow...")
    flow.collect { value -> println(value) }
    println("Calling again...")
    flow.collect { value -> println(value) }
}
// (1초 후)
// Calling flow...
// Flow started
// (1초 후)
// 1
// (1초 후)
// 2
// (1초 후)
// 3
// Calling again...
// Flow started
// (1초 후)
// 1
// (1초 후)
// 2
// (1초 후)
// 3