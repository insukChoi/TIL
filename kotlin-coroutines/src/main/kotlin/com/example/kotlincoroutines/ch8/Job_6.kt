package com.example.kotlincoroutines.ch8

import kotlinx.coroutines.*

fun main() {
    val messageProcessor = MessageProcessor()

    messageProcessor.processMessage("insuk")

    println("END")
}

object DBUpsertException: CancellationException("DB upsert Exception")
object KafkaSendingException: CancellationException("Kafka Sending Exception")

class MessageProcessor {
    fun processMessage(message: String) = runBlocking {
        launch {
//            delay(3000)
            println("DB = $message")
            if (DBService().upsert() != 1) throw DBUpsertException
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

class DBService {
    fun upsert(): Int {
        return 2
    }
}