package com.example.kotlincoroutines.ch16

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main(): Unit = coroutineScope {
    val channel = produce {
        repeat(5) { index ->
            println("Producing next one")
            kotlinx.coroutines.delay(1000)
            send(index * 2)
        }
    }

    for (element in channel) {
        println(element)
    }
}

// ReceiveChannel 을 반환하는 코루틴 빌더인 produce 함수를 사용하는 것이 좀더 편리합니다.
// produce 함수는 빌더로 시작된 코루틴이 어떻게 종료되든 상관없이(끝나거나, 중단되거나, 취소되거나) 채널을 닫습니다.
// 따라서 close 를 반드시 호출하기 때문에 produce 빌더는 채널을 만드는 가장 인기 있는 방법이고 안전하고 편리하다.