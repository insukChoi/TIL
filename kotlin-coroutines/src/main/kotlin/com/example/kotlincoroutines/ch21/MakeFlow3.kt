package com.example.kotlincoroutines.ch21

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow

suspend fun getUserName(): String {
    delay(1000)
    return "UserName"
}

suspend fun main() {
    // 일반 함수를 변경하려면 함수 참조값이 필요
    ::getUserName
        .asFlow()
        .collect {
            println(it)
        }
}
// (1초 후)
// UserName