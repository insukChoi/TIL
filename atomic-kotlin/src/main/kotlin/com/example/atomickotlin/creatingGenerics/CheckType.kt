package com.example.atomickotlin.creatingGenerics

import com.example.atomickotlin.atomicTest.eq

inline fun <reified T> check(t: Any) = t is T

fun main() {
    check<String>("1") eq true
    check<Int>("1") eq false
}