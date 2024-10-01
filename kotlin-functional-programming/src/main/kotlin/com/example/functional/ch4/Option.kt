package com.example.functional.ch4

sealed class Option<out A>

data class Some<out A>(val get: A) : Option<A>()

data object None : Option<Nothing>()

fun mean(xs: List<Double>) : Option<Double> =
    if (xs.isEmpty()) None else Some(xs.average())