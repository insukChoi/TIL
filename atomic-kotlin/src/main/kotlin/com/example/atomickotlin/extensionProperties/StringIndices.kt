package com.example.atomickotlin.extensionProperties

import com.example.atomickotlin.atomicTest.eq

val String.indices: IntRange
    get() = 0 until length

fun main() {
    "abc".indices eq 0..2
}