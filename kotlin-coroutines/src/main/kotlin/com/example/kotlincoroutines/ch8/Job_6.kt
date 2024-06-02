package com.example.kotlincoroutines.ch8

import kotlinx.coroutines.*

fun main() {
    val messageProcessor = MessageProcessor()

    messageProcessor.processMessage("insuk")

    println("END")
}

object DBUpsertException : CancellationException("DB upsert Exception")
object KafkaSendingException : CancellationException("Kafka Sending Exception")

class MessageProcessor {
    fun processMessage(message: String) = runBlocking {
        withContext(Dispatchers.IO) {
            launch {
                delay(500)
                println("DB = $message")
                throw DBUpsertException // supervisor job 대신 부모까지 예외가 전파되지 않는 CancellationException 사용
            }

            launch {
                runCatching {
                    delay(1000)
                    println("Kafka = $message")
                }.onFailure {
                    println("kafka failed")
                }
            }
        }
    }
}

class DBService {
    fun upsert(): Int {
        return 2
    }
}