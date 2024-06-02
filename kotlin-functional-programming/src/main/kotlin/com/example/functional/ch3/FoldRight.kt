package com.example.functional.ch3

fun <A, B>foldRight(xs: List<A>, z: B, f: (A, B) -> B): B =
    when (xs) {
        is Nil -> z
        is Cons -> f(xs.head, foldRight(xs.tail, z, f))
    }

fun sum2(ints: List<Int>): Int =
    foldRight(ints, 0) { x, y -> x + y }

fun product2(dbs: List<Double>): Double =
    foldRight(dbs, 1.0) { x, y -> x * y }

fun <A> length(xs: List<A>): Int =
    foldRight(xs, 0) { _, acc -> 1 + acc}