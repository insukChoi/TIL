package com.example.atomickotlin.introGenerics

import com.example.atomickotlin.atomicTest.eq

class AnyHolder(private val value: Any) {
    fun getValue() = value
}

class Dog {
    fun bark() = "Ruff!"
}

fun main() {
    val holder = AnyHolder(Dog())
    val any = holder.getValue()
    // any.bark() 컴파일 에러

    val genericHolder = GenericHolder(Dog())
    val dog = genericHolder.getValue()
    dog.bark() eq "Ruff!"
}