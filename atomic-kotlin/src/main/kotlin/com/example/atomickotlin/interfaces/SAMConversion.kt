package com.example.atomickotlin.interfaces

import com.example.atomickotlin.atomicTest.trace

fun interface Action {
    fun act()
}

fun delayAction(action: Action) {
    trace("Delaying...")
    action.act()
}

fun main() {
    delayAction {
        trace("Hey!")
    }

    trace eq "Delaying... Hey!"
}