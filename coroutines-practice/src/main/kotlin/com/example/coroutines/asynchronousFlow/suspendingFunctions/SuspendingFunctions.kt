package com.example.coroutines.asynchronousFlow.suspendingFunctions

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

suspend fun simple(): List<Int> {
    delay(1000)
    return listOf(1, 2, 3)
}

fun main() = runBlocking {
    simple().forEach { value -> println(value) }
}