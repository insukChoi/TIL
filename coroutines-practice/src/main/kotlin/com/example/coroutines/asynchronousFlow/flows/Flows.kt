package com.example.coroutines.asynchronousFlow.flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun simple(): Flow<Int> = flow {// flow builder
    for (i in 1..3) {
        delay(100)
        emit(1) // emit next value
    }
}

fun main() = runBlocking<Unit> {
    launch {
        for (k in 1..3) {
            println("I'm not blocking $k")
        }
    }
    // collect the flow
    com.example.coroutines.asynchronousFlow.flowAreCold.simple().collect {
        value -> println(value)
    }
}