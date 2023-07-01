package com.example.atomickotlin.extensionProperties

import com.example.atomickotlin.atomicTest.eq

fun main() {
    val list: List<*> = listOf(1, 2)
    // List<*> 를 사용하면 List에 담긴 원소의 타입 정보를 모두 잃어버린다. List<*>에서 얻은 원소는 Any? 에만 대입할 수 있음.
    val any: Any? = list[0]
    any eq 1
}