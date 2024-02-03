package com.example.kotlincoroutines.ch11

import kotlinx.coroutines.*

fun CoroutineScope.log(text: String) {
    val name = this.coroutineContext[CoroutineName]?.name
    println("[$name] $text")
}

fun main() = runBlocking(CoroutineName("Parent")) {
    log("Before")

    withContext(CoroutineName("Child 1")) {
        delay(1000)
        log("Hello 1")
    }

    withContext(CoroutineName("Child 2")) {
        delay(1000)
        log("Hello 2")
    }

    log("After")
}
// [Parent] Before
// (1초 후)
// [Child 1] Hello 1
// (1초 후)
// [Child 2] Hello 2
// [Parent] After

// coroutineScope { /*..*/ } 가 작동하는 방식이 async { /*..*/ }.await() 처럼 async의 await를 곧바로 호출하는 것과 비슷하다.
// withContext(context) 또한 async(context) { /*..*/ }.await() 와 비슷합니다.
// 가장 큰 차이는 async 는 스코프를 필요로 하지만, coroutineScope 와 withContext는 해당 함수를 호출한 중단점에서 스코프를 들고온다는 것