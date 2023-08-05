package com.example.atomickotlin.operatorOverloading

import com.example.atomickotlin.atomicTest.eq

class A(val i: Int)

data class D(val i: Int)

fun main() {
    // 일반 클래스
    val a = A(1)
    val b = A(1)
    val c = a
    (a == b) eq false
    (a == c) eq true

    // 데이터 클래스
    val d = D(1)
    val e = D(1)
    (d == e) eq true
}