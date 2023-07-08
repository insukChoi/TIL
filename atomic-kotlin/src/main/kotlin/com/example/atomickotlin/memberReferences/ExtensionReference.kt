package com.example.atomickotlin.memberReferences

import com.example.atomickotlin.atomicTest.eq

fun Int.times47() = times(47)

class Frog
fun Frog.speak() = "Rabbit!"

fun goInt(n: Int, g: (Int) -> Int) = g(n)
fun goFrog(frog: Frog, g: (Frog) -> String) = g(frog)

fun main() {
    goInt(12, Int::times47) eq 564
    goInt(5) { n: Int -> n * 5 } eq 25
    goFrog(Frog(), Frog::speak) eq "Rabbit!"
}