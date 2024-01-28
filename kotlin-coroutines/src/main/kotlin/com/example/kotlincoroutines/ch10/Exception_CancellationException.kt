package com.example.kotlincoroutines.ch10

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException

object MyNonPropagationException : CancellationException()

suspend fun main(): Unit = coroutineScope {
    launch {
        launch {
            delay(2000)
            println("will not be printed")
        }
        throw MyNonPropagationException // 예외가 CancellationException 의 서브클래스라면 부모로 전파되지 않습니다.
    }
    launch {
        delay(2000)
        println("Will be printed")
    }
}