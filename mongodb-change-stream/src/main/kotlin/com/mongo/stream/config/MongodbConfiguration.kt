package com.mongo.stream.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.boot.autoconfigure.mongo.MongoProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.*
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory
import org.springframework.data.mongodb.ReactiveMongoTransactionManager
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories


const val MONGO_TRANSACTION_MANAGER = "mongoTransactionManager"
const val BASE_PACKAGES = "com.mongo.stream.domain"

@Configuration
@EnableReactiveMongoRepositories(basePackages = [BASE_PACKAGES])
@EnableConfigurationProperties(MongoProperties::class)
@EnableMongoAuditing
class MongodbConfiguration(
    private val mongoProperties: MongoProperties
) : AbstractReactiveMongoConfiguration() {

    @Bean(MONGO_TRANSACTION_MANAGER)
    fun transactionManager(reactiveMongoDatabaseFactory: ReactiveMongoDatabaseFactory): ReactiveMongoTransactionManager {
        return ReactiveMongoTransactionManager(reactiveMongoDatabaseFactory)
    }

    @Bean
    fun mongoClient(): MongoClient {
        val connectionString = ConnectionString(mongoProperties.uri)
        return MongoClients.create(
            MongoClientSettings.builder()
                .apply {
                    applyConnectionString(connectionString)
                }
                .build()
        )
    }

    @Bean
    fun reactiveMongoTemplate(): ReactiveMongoTemplate? {
        return ReactiveMongoTemplate(mongoClient(), databaseName)
    }

    override fun getDatabaseName(): String =
        mongoProperties.database
}