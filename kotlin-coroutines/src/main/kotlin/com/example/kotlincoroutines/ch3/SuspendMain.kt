package com.example.kotlincoroutines.ch3

import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


suspend fun main() {
    println("Before")

    suspendCoroutine { continuation ->
        println(continuation)
        continuation.resume(Unit)
    }

    println("After")
}