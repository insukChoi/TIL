package com.example.atomickotlin.creatingGenerics

class Box<T>(private var contents: T) {
    fun put(item: T) {
        contents = item
    }

    fun get(): T = contents
}

class InBox<in T>(private var contents: T) {
    fun put(item: T) {
        contents = item
    }
}

class OutBox<out T>(private var contents: T) {
    fun get(): T = contents
}

open class Pet
class Cat : Pet()
class Dog : Pet()

val catBox = Box<Cat>(Cat())
// val petBox: Box<Pet> = catBox


val outCatBox: OutBox<Cat> = OutBox(Cat())
val outPetBox: OutBox<Pet> = outCatBox
val outAnyBox: OutBox<Any> = outCatBox

fun getting() {
    val cat: Cat = outCatBox.get()
    val pet: Pet = outPetBox.get()
    val any: Any = outAnyBox.get()
}