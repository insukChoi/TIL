package com.example.atomickotlin.sequence

import com.example.atomickotlin.atomicTest.capture
import com.example.atomickotlin.atomicTest.eq

fun main() {
    val items = mutableListOf(
        "first", "second", "third", "XXX", "4th"
    )
    val seq = generateSequence {
        items.removeAt(0).takeIf { it != "XXX" }
    }

    seq.toList() eq "[first, second, third]"
    capture {
        seq.toList()
    } eq "IllegalStateException: This " +
            "sequence can be consumed only once."
}