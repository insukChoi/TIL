package com.example.kotlincoroutines.ch22

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * 플로우를 완료할 수 있는 여러가지 방법이 있습니다.
 * 잡히지 않은 예외가 발생했거나 코루틴이 취소되었을 때도 포함되지만,
 * 가장 흔한 방법은 플로우 빌더가 끝났을때(마지막 원소가 전송되었을때)입니다.
 *
 * onCompletion 메서드를 사용해 플로우가 완료되었을 때 호출되는 리스너를 추가할 수 있습니다.
 */
suspend fun main() {
    flowOf(1, 2)
        .onEach { delay(1000) }
        .onCompletion { println("Completion") }
        .collect { println(it) }
}
// (1초 후)
// 1
// (1초 후)
// 2
// Completion



//suspend fun main() = coroutineScope {
//    val job = launch {
//        flowOf(1, 2)
//            .onEach { delay(1000) }
//            .onCompletion { println("Completion") }
//            .collect { println(it) }
//    }
//
//    delay(1100)
//    job.cancel()
//}
// (1초 후)
// 1
// (1초 후)
// Completion