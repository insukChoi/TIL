package `6_ DesignedForConcurrency`

import kotlinx.coroutines.*
import kotlin.random.Random

/**
 * 값 지연 패턴은 비동기 계산 로직이 결과를 직접 반환하는 대신 결과값을 가리키는 참조를 반환하도록 한다.
 * 자바와 스칼라의 Future, 자바스크립트의 Promise 가 모두 값 지연 패턴을 구현한다.
 */
fun main() = runBlocking {
    val value = valueAsync()
    println(value.await())
}

suspend fun valueAsync(): Deferred<String> = coroutineScope {
    val deferred = CompletableDeferred<String>()
    launch {
        delay(100)
        if (Random.nextBoolean()) {
            deferred.complete("OK")
        }
        else {
            deferred.completeExceptionally(
                RuntimeException()
            )
        }
    }
    deferred
}