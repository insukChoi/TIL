package com.example.atomickotlin.sequence

import com.example.atomickotlin.atomicTest.eq

fun main() {
    val naturalNumbers = generateSequence(1) {
        it + 1
    }

    naturalNumbers.take(3).toList() eq listOf(1, 2, 3)

    naturalNumbers.take(10).sum() eq 55
}