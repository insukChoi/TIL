package com.example.junit5referencepractice

class Calculator {
    fun add(a: Int, b: Int): Int {
        return a + b
    }

    fun div(a: Int, b: Int): Double {
        assert(b != 0) { "Division by Zero" }
        return a / b * 1.0
    }

    fun divide(a: Int,b : Int): Int {
        return a/b
    }

    fun multiply(a: Int, b: Int): Int {
        return a * b
    }
}