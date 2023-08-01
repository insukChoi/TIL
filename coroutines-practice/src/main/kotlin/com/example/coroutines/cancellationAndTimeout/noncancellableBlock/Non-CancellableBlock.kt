package com.example.coroutines.cancellationAndTimeout.noncancellableBlock

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        }
        catch (e: Throwable) {
            println("e : ${e.message}")
        }
        finally {
            // 이전 예제의 finally 블록에서 정지 함수를 사용하려고 하면 이 코드를 실행하는 코루틴이 취소되기 때문에 CancellationException이 발생합니다.
            // 정상적으로 작동하는 모든 닫기 작업(파일 닫기, 작업 취소 또는 모든 종류의 통신 채널 닫기)은 일반적으로 차단하지 않으며 일시 중단 기능을 포함하지 않기 때문에 일반적으로 이것은 문제가 되지 않습니다.
            // 그러나 드문 경우지만 취소된 코루틴에서 일시 중단해야 하는 경우 다음 예제와 같이 withContext(NonCancellable) {...} withContext 함수 및 NonCancellable 컨텍스트를 사용하여 해당 코드를 래핑할 수 있습니다.
            withContext(NonCancellable) {
                println("job: I'm running finally")
                delay(1000L)
                println("job: And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }
    }
    delay(1300L)
    println("main: I'm tired of waiting!")
    job.cancelAndJoin()
    println("main: Now I can quit.")
}