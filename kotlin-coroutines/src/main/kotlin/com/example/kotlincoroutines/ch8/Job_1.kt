package com.example.kotlincoroutines.ch8

import kotlinx.coroutines.*

suspend fun main() = coroutineScope {
    // 빌더로 생성된 잡은
    val job = Job()
    println(job) // JobImpl{Active}@20fa23c1
    // 메서드로 완료시킬 때까지 Active 상태입니다.
    job.complete()
    println(job) // JobImpl{Completed}@20fa23c1

    val activeJob = launch {
        delay(1000)
    }
    println(activeJob) // StandaloneCoroutine{Active}@4f4a7090
    // 여기서 잡이 완료될 때까지 기다립니다.
    activeJob.join() // (1초 후)
    println(activeJob) // StandaloneCoroutine{Completed}@4f4a7090

    // launch 는 New 상태로 지연 시작됩니다.
    val lazyJob = launch(start = CoroutineStart.LAZY) {
        delay(1000)
    }
    println(lazyJob) // LazyStandaloneCoroutine{New}@59e9c510
    // Active 상태가 되려면 시작하는 함수를 호출해야 합니다.
    lazyJob.start()
    println(lazyJob) // LazyStandaloneCoroutine{Active}@59e9c510
    lazyJob.join() // (1초 후)
    println(lazyJob) // LazyStandaloneCoroutine{Completed}@59e9c510
}