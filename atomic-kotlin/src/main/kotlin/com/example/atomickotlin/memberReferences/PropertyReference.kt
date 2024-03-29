package com.example.atomickotlin.memberReferences

import com.example.atomickotlin.atomicTest.eq

data class Message(
    val sender: String,
    val text: String,
    val isRead: Boolean
)

fun main() {
    val messages = listOf(
        Message("Kitty", "Hey!", true),
        Message("Kitty", "Where are you?", false)
    )

    val unread = messages.filterNot(Message::isRead)

    unread.size eq 1
    unread.single().text eq "Where are you?"
}