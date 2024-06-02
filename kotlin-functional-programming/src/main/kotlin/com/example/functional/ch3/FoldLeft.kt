package com.example.functional.ch3

tailrec fun <A,B> foldLeft(xs: List<A>, z: B, f: (B, A) -> B): B =
    when (xs) {
        is Nil -> z
        is Cons -> foldLeft(xs.tail, f(z, xs.head), f)
    }

fun sum3(ints: List<Int>): Int =
    foldLeft(ints, 0) { x, y -> x + y }

fun product3(dbs: List<Double>): Double =
    foldLeft(dbs, 1.0) { x, y -> x * y }