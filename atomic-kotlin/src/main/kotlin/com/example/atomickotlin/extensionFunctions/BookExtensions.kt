package com.example.atomickotlin.extensionFunctions

import com.example.atomickotlin.atomicTest.eq

class Book(val title: String)

fun Book.categorize(category: String) = """title: "$title", category: $category"""

fun main() {
    Book("Dracula").categorize("Vampire") eq """title: "Dracula", category: Vampire"""
}