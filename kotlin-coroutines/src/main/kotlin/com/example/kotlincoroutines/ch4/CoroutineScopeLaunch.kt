package com.example.kotlincoroutines.ch4

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() = coroutineScope {
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}