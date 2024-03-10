package com.example.kotlincoroutines.ch24

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    val mutableSharedFlow = MutableSharedFlow<String>(replay = 0)

    launch {
        mutableSharedFlow.collect {
            println("#1 received $it")
        }
    }

    launch {
        mutableSharedFlow.collect {
            println("#2 received $it")
        }
    }

    delay(1000)
    mutableSharedFlow.emit("Message1")
    mutableSharedFlow.emit("Message2")
}
// (1초 후)
// #2 received Message1
// #2 received Message2
// #1 received Message1
// #1 received Message2
// (프로그램은 절대 끝나지 않습니다.)

/**
 * 위 프로그램은 coroutineScope 의 자식 코루틴이 launch 로 시작된 뒤
 * MutableSharedFlow 를 감지하고 있는 상태이므로 종료되지 않습니다.
 */