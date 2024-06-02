package com.example.functional.ch3

sealed class List<out A>

data object Nil : List<Nothing>()

data class Cons<out A>(val head: A, val tail: List<A>) : List<A>()
