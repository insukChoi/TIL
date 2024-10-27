package `5_dataFlowControl`

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

suspend fun main() {
    val numbersFlow: Flow<Int> = flow {
        (0..10).forEach {
            println("$it 전송 중")
            emit(it)

            if (it == 7) {
                throw RuntimeException()
            }
        }
    }

    try {
        numbersFlow.collect { println("$it 수신") }
    } catch (e: Exception) {
        println("예외 처리 중 ")
    }
}
/**
 * 0 전송 중
 * 0 수신
 * 1 전송 중
 * 1 수신
 * 2 전송 중
 * 2 수신
 * 3 전송 중
 * 3 수신
 * 4 전송 중
 * 4 수신
 * 5 전송 중
 * 5 수신
 * 6 전송 중
 * 6 수신
 * 7 전송 중
 * 7 수신
 * 예외 처리 중
 *
 */