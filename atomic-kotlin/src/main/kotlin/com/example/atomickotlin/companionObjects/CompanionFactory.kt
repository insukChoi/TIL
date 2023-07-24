package com.example.atomickotlin.companionObjects

import com.example.atomickotlin.atomicTest.eq

// 팩토리 메서드 패턴
class Numbered2 private constructor(
    private val id: Int
) { // Numbered2 의 비공개 생성자
    override fun toString(): String = "#$id"
    companion object Factory {
        fun create(size: Int) = List(size) {
            Numbered2(it)
        }
    }
}

fun main() {
    Numbered2.create(0) eq "[]"
    Numbered2.create(4) eq "[#0, #1, #2, #3]"
}