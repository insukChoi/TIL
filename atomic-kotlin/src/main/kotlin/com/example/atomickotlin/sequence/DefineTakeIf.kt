package com.example.atomickotlin.sequence

import com.example.atomickotlin.atomicTest.eq

fun <T> T.takeIf(
    predicate: (T) -> Boolean
): T? {
    return if(predicate(this)) this else null
}

fun main() {
    "abc".takeIf { it != "XXX" } eq "abc"
    "XXX".takeIf { it != "XXX" } eq null
}