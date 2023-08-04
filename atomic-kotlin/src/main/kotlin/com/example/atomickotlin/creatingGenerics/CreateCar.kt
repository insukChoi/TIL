package com.example.atomickotlin.creatingGenerics

import com.example.atomickotlin.atomicTest.eq

class Car {
    override fun toString(): String = "Car"
}

open class Create<T>(private var contents: T) {
    fun put(item: T) {
        contents = item
    }

    fun get(): T = contents
}

fun <T, R> Create<T>.map(f: (T) -> R): List<R> = listOf(f(get()))

fun main() {
    val cc = Create(Car())
    val car: Car = cc.get()
    car eq "Car"

    Create(Car()).map { it.toString() + "x" } eq "[Carx]"
}