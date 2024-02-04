package com.example.kotlincoroutines.ch13

import kotlinx.coroutines.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MakeCoroutine {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Bean("coroutineDispatcher")
    fun coroutineDispatcher(): CoroutineDispatcher =
        Dispatchers.IO.limitedParallelism(5)

    @Bean("coroutineExceptionHandler")
    fun coroutineExceptionHandler() =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            throwable.message
            // FilebaseCrashlytics.getInstance().recordException(throwable)
        }

    @Bean
    fun coroutineScope(
        coroutineDispatcher: CoroutineDispatcher,
        coroutineExceptionHandler: CoroutineExceptionHandler
    ) = CoroutineScope(
        SupervisorJob() +
                coroutineDispatcher +
                coroutineExceptionHandler
    )
}