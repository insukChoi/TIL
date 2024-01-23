package com.example.kotlincoroutines.ch3

import kotlinx.coroutines.delay
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


suspend fun main() {
    println("Before")

    delay(1000)

    println("After")
}