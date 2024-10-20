package functionalProgramming

import kotlin.concurrent.thread

data class Player(var score: Int)

fun main() {
    val scores = listOf(Player(0))
    val threads = List(2) {
        thread {
            for (i in 1..1000) {
                scores[0].score++
            }
        }
    }

    for (t in threads) {
        t.join()
    }

    println(scores[0].score) // 2000 보다는 작은 값
}

/**
 * 위 예제는 가변 변수를 사용할 때 발생하는 '경합 조건'을 보여주는 고전적인 예씨다.
 *
 * 이 문제의 진짜 원인은 더하기 연산과 할당 연산이 원자적(atomic)이지 않다는 데에 있다
 * 두 스레드가 서로의 더하기 연산 결과를 덮어써 버릴 수 있기 때문에 값이 원하는 만큼 증가하지 않는 것이다.
 */