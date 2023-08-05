package com.example.atomickotlin.operatorOverloading

import com.example.atomickotlin.atomicTest.eq

operator fun String.invoke(
    f: (s: String) -> String
) = f(this)

fun main() {
    "mumbling" { it.uppercase() } eq "MUMBLING"
}