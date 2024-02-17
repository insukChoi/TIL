package com.example.kotlincoroutines.ch17

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

/**
 * 채널은 핫 데이터 스트림이기 때문에 소비되는 것과 상관없이 값을 생성한 뒤에 가지게 됩니다.
 * 수신자가 얼마나 많은지에 대해선 신경 쓰지 않습니다.
 * 각 원소는 단 한번만 받을 수 있기 때문에, 첫 번째 수신자가 모든 원소를 소비하고 나면 두 번째 소비자는 채널이 비어 있으며
 * 이미 닫혀 있다는 걸 반견하게 됩니다. 따라서 두 번째 소비자는 어떤 원소도 받을 수가 없습니다.
 */
private fun CoroutineScope.makeChannel() = produce {
    println("Channel started")
    for (i in 1..3) {
        delay(1000)
        send(i)
    }
}

suspend fun main() = coroutineScope {
    val channel = makeChannel()

    delay(1000)
    println("Calling channel...")
    for (value in channel) {
        println(value)
    }
    println("Calling again...")
    for (value in channel) {
        println(value)
    }
}
// Channel started
// (1초 후)
// Calling channel...
// 1
// (1초 후)
// 2
// (1초 후)
// 3
// Consuming again...