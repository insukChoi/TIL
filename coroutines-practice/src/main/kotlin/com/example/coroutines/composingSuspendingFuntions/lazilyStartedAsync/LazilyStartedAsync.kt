package com.example.coroutines.composingSuspendingFuntions.lazilyStartedAsync

import com.example.coroutines.composingSuspendingFuntions.sequentialByDefault.doSomethingUsefulOne
import com.example.coroutines.composingSuspendingFuntions.sequentialByDefault.doSomethingUsefulTwo
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) {
            doSomethingUsefulOne()
        }
        val two = async(start = CoroutineStart.LAZY) {
            doSomethingUsefulTwo()
        }
        // some computation
        one.start() // start the first one
        two.start() // start the second one
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}