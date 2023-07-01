package com.example.atomickotlin.introGenerics

import com.example.atomickotlin.atomicTest.eq

fun <T> identity(arg: T): T = arg

fun main() {
    identity("Yellow") eq "Yellow"
    identity(1) eq 1
    val d: Dog = identity(Dog())
    d.bark() eq "Ruff!"
}