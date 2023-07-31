package com.example.atomickotlin.extensionLambdas

import com.example.atomickotlin.atomicTest.eq

val va: (String, Int) -> String = { str, n ->
    str.repeat(n) + str.repeat(n)
}

val vb: String.(Int) -> String = {
    this.repeat(it) + repeat(it)
}

fun main() {
    va("Vanbo", 2) eq "VanboVanboVanboVanbo"
    "Vanbo".vb(2) eq "VanboVanboVanboVanbo"
    vb("Vanbo", 2) eq "VanboVanboVanboVanbo"
}