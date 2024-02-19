package com.example.kotlincoroutines.ch19

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// 플로우 빌더는 중단 함수가 아니기 때문에
// CoroutineScope 가 필요하지 않습니다.
fun userFlow(): Flow<String> = flow {
    repeat(3) {
        delay(1000)
        val ctx = currentCoroutineContext()
        val name = ctx[CoroutineName]?.name
        emit("User$it in $name")
    }
}

suspend fun main() {
    val users = userFlow()

    withContext(CoroutineName("Name")) {
        val job = launch {
            // collect 는 중단 함수입니다.
            users.collect {
                println(it)
            }
        }

        launch {
            delay(2100)
            println("I got enough")
            job.cancel()
        }
    }
}
// (1초 후)
// User0 in Name
// (1초 후)
// User1 in Name
// (0.1초 후)
// I got enough

// => 3번 돌기전에 Job 이 취소됨