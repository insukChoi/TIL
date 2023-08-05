package com.example.atomickotlin.operatorOverloading

import com.example.atomickotlin.atomicTest.eq

class E(var v: Int) {
    override fun equals(other: Any?): Boolean = when {
        this === other -> true
        other !is E -> false
        else -> v == other.v
    }

    override fun hashCode(): Int = v
    override fun toString(): String = "E($v)"
}

data class R(val r: IntRange) {
    override fun toString(): String = "R($r)"
}

operator fun E.rangeTo(e: E) = R(v..e.v)

operator fun R.contains(e: E) = e.v in r

fun main() {
    val a = E(2)
    val b = E(3)
    val r = a..b
    (a in r) eq true
    (a !in r) eq false
    r eq R(2..3)
}