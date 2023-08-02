package com.example.coroutines.composingSuspendingFuntions.structuredConcurrencyWithAsync

import com.example.coroutines.composingSuspendingFuntions.sequentialByDefault.doSomethingUsefulOne
import com.example.coroutines.composingSuspendingFuntions.sequentialByDefault.doSomethingUsefulTwo
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    one.await() + two.await()
}

fun main() = runBlocking {
    val time = measureTimeMillis {
        println("The answer is ${concurrentSum()}")
    }
    println("Completed in $time ms")
}