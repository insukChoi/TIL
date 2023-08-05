package com.example.atomickotlin.usingOperators

import com.example.atomickotlin.atomicTest.eq

fun main() {
    val list = MutableList(10) { 'a' + it }
    list[7] eq 'h' // operator get()
    list.get(8) eq 'i' // 명시적 호출
    list[9] = 'x' // operator set()
    list.set(9, 'x') // 명시적 호출
    list[9] eq 'x'
    ('d' in list) eq true // operator contains()
    list.contains('e') eq true // 명시적 호출
}