package com.example.kotlincoroutines.ch10

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    /*
     * 예외는 자식에서 부모로 전파되며, 부모가 취소되면 자식도 취소되기 때문에 쌍방으로 전파됩니다.
     * 예외 전파가 정지되지 않으면 계통 구조상 모든 코루틴이 취소되게 됩니다.
     */
    launch {
        launch {
            delay(1000)
            throw Error("Some error")
        }

        launch {
            delay(2000)
            println("Will not be printed")
        }

        launch {
            delay(500) // 예외 발생보다 빠릅니다.
            println("Will be printed")
        }
    }

    launch {
        delay(2000)
        println("Will not be printed")
    }
}