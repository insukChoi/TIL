package com.example.kotlincoroutines.ch4

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch {
        delay(1000L)
        println("World!")
    }
    launch {
        delay(2000L)
        println("World!")
    }
    println("Hello,")
}