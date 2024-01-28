package com.example.kotlincoroutines.ch8

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    launch(Job()) { // 새로운 잡이 부모로부터 상속받은 잡을 대체합니다.
        delay(1000)
        println("Will not be printed")
    }
}
// (아무것도 출력하지 않고, 즉시 종료합니다.)