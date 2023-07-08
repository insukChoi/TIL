package com.example.atomickotlin.memberReferences

import com.example.atomickotlin.atomicTest.eq

fun main() {
    val messages = listOf(
        Message("Kitty", "Hey!", true),
        Message("Kitty", "Where are you?", false),
        Message("Boss", "Meeting today", false)
    )

    messages.sortedWith(compareBy(
        Message::isRead, Message::sender)) eq listOf(
            Message("Boss", "Meeting today", false),
            Message("Kitty", "Where are you?", false),
            Message("Kitty", "Hey!", true)
        )
}