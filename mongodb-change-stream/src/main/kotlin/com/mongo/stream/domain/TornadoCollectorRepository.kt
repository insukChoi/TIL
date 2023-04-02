package com.mongo.stream.domain

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface TornadoCollectorRepository : ReactiveMongoRepository<TornadoCollector, Long> {
    fun findAllByAid(aid: String): Flux<TornadoCollector>
}