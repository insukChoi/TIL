package com.example.atomickotlin.companionObjects

import com.example.atomickotlin.atomicTest.trace

class CompanionInit {
    init {
        trace("This Class Constructor")
    }

    companion object {
        init {
            trace("Companion Constructor")
        }
    }
}

fun main() {
    trace("Before")
    CompanionInit()
    trace("After1")
    CompanionInit()
    trace("After2")
    CompanionInit()
    trace("After3")
    trace eq """
        Before
        Companion Constructor
        This Class Constructor
        After1
        This Class Constructor
        After2
        This Class Constructor
        After3
    """.trimIndent()
}