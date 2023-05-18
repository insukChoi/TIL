package com.insuk.kotest.dataDrivenTesting

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

/*
 * Before data-driven-testing can be used,
 * you need to add the module kotest-framework-datatest to your build.
 */

fun isPythagTriple(a: Int, b: Int, c: Int): Boolean = a * a + b * b == c * c

data class PythagTriple(val a: Int, val b: Int, val c: Int)

class MyTests: FunSpec({

    beforeTest {
        println("pythag !!!")
    }

    context("Pythag triples tests") {
        withData(
            PythagTriple(3,4,5),
            PythagTriple(6,8,10),
            PythagTriple(8,15,17),
            PythagTriple(7,24,25)
        ) { (a,b,c) ->
            isPythagTriple(a, b, c) shouldBe true
        }
    }
})