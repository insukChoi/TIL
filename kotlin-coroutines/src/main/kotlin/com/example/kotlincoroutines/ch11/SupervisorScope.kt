package com.example.kotlincoroutines.ch11

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

fun main() = runBlocking {
    println("Before")

    supervisorScope {
        launch {
            delay(1000)
            throw Error()
        }

        launch {
            delay(2000)
            println("Done")
        }
    }

    println("After")
}
// Before
// (1초 후)
// 예외가 발생합니다..
// (1초 후)
// Done
// After