package com.example.atomickotlin.lazyInitialization

import com.example.atomickotlin.atomicTest.trace

val idle: String by lazy {
    trace("Initializing 'idle'")
    "I'm never used"
}

val helpful: String by lazy {
    trace("Initializing 'helpful'")
    "I'm helping!"
}

fun main() {
    trace(helpful)
    trace eq """
Initializing 'helpful'
I'm helping!
    """.trimIndent()
}