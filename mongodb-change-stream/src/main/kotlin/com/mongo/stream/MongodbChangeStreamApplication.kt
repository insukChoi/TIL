package com.mongo.stream

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MongodbChangeStreamApplication

fun main(args: Array<String>) {
    runApplication<MongodbChangeStreamApplication>(*args)
}
