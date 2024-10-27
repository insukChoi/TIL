package `6_ DesignedForConcurrency`

import kotlinx.coroutines.*
import java.util.concurrent.ForkJoinPool

/**
 * 스케줄러 패턴의 목적은 실행의 대상과 방법을 분리하고, 실행에 소요되는 자원 사용을 최적화하는 것이다.
 * 코틀린에서는 분배기(Dispatcher)가 스케줄러 디자인 패턴을 구현하고 있다.
 */
fun main(): Unit = runBlocking {
//    launch {
//        // main 을 출력
//        println(Thread.currentThread().name)
//    }
//    launch(Dispatchers.Default) {
//        // DefaultDispatcher-worker-1 을 출력
//        println(Thread.currentThread().name)
//    }
//    async(Dispatchers.IO) {
//        // DefaultDispatcher-worker-1~3 을 출력
//        // 이 예제는 그다지 오래 걸리지 않기 때문에 입출력 분배기가 많은 스레드를 만들지 않아도 됐다 (최대 64개)
//        for (i in 1..1000) {
//            println(Thread.currentThread().name)
//            yield()
//        }
//    }

    /* 스케줄러 직접 만들기 */
    val forkJoinPool = ForkJoinPool(4).asCoroutineDispatcher()

    repeat(100) {
        launch(forkJoinPool) {
            // ForkJoinPool-1-worker-1~4 을 출력
            println(Thread.currentThread().name)
        }
    }
    // 직접 분배기를 만들었다면 다른 곳에서 재활용할 계획이 아닌 이상 close()를 호출해서 자원을 해제해야한다.
    forkJoinPool.close()
}
