package com.example.atomickotlin.extensionFunctions

import com.example.atomickotlin.atomicTest.eq

fun String.singleQuote() = "'$this'"
fun String.doubleQuote() = "\"$this\""
fun String.strangeQuote() = this.singleQuote().singleQuote()

fun main() {
    "Hi".singleQuote() eq "'Hi'"
    "Hi".doubleQuote() eq "\"Hi\""
    "Hi".strangeQuote() eq "\'\'Hi\'\'"
}