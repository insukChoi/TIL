package com.example.atomickotlin.lazyInitialization

import com.example.atomickotlin.atomicTest.trace

class WithLate {
    lateinit var x: String
    fun state() = "${::x.isInitialized}"
}

lateinit var y: String

fun main() {
    trace("${::y.isInitialized}")
    y = "Ready"
    trace("${::y.isInitialized}")
    val withlate = WithLate()
    trace(withlate.state())
    withlate.x = "Set"
    trace(withlate.state())

    trace eq "false true false true"
}