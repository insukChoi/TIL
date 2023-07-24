package com.example.atomickotlin.companionObjects

import com.example.atomickotlin.atomicTest.eq

class Counted {
    companion object {
        private var count = 0
    }
    private val id = count++
    override fun toString(): String = "#$id"
}

fun main() {
    List(4) {
        Counted()
    } eq "[#0, #1, #2, #3]"
}