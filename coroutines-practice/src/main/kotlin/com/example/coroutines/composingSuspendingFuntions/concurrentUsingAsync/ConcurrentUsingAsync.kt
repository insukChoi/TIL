package com.example.coroutines.composingSuspendingFuntions.concurrentUsingAsync

import com.example.coroutines.composingSuspendingFuntions.sequentialByDefault.doSomethingUsefulOne
import com.example.coroutines.composingSuspendingFuntions.sequentialByDefault.doSomethingUsefulTwo
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        val one = async {
            doSomethingUsefulOne()
        }
        val two = async {
            doSomethingUsefulTwo()
        }
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}