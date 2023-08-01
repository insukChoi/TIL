package com.example.coroutines.cancellationAndTimeout.asynchronousTimeoutAndResources

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

var acquired = 0

class Resource {
    init {
        acquired++ // Acquire the resource
    }
    fun close() { acquired-- } // Release the resource
}

fun main() {
    runBlocking {
        repeat(10_000) {
            // Launch 10K coroutines
            launch {
                val resource = withTimeout(60) { // Timeout of 60 ms
                    delay(50) // Delay for 50ms
                    Resource()
                }
                resource.close() // Release the resource
            }        }
    }
    // Outside runBlocking all coroutines have completed
    println(acquired) // Print the number of resources still acquired
}