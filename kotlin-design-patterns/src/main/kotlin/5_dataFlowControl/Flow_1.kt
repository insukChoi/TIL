package `5_dataFlowControl`

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Flow 는 Cold 비동기 스트림이다
 */
suspend fun main() {
    val numbersFlow: Flow<Int> = flow {
        (0..10).forEach {
            println("$it 전송 중")
            emit(it)
        }
    }

    numbersFlow.collect { println("$it 수신") }
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
 * 8 전송 중
 * 8 수신
 * 9 전송 중
 * 9 수신
 * 10 전송 중
 * 10 수신
 */