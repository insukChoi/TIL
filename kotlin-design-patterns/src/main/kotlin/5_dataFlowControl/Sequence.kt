package `5_dataFlowControl`

import kotlin.system.measureTimeMillis

/**
 * 순서열(Sequence)는 lazy 하지만, 집합 자료구조는 eager 하다
 */
fun main() {
    val numbers = (1..10_000).toList()
    println("집합 자료구조 = "+
        measureTimeMillis {
            numbers.map {
                numbers.map {
                    it * it
                }
            }.take(1).forEach { it }
        }
    )
    // 1059ms

    println("순서열 = "+
        measureTimeMillis {
            numbers.asSequence().map {
                numbers.map {
                    it * it
                }
            }.take(1).forEach { it }
        }
    )
    // 4ms
}