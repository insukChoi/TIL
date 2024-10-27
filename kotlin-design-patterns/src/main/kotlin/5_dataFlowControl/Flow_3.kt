package `5_dataFlowControl`

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    val numbersFlow: Flow<Int> = flow {
        (0..10).forEach {
            println("$it 전송 중")
            emit(it)
        }
    }

    (1..4).forEach { coroutineId ->
        delay(5000)
        launch(Dispatchers.Default) {
            numbersFlow.collect { number ->
                delay(1000)
                println("${coroutineId}번 코루틴에서 $number 수신")
            }
        }
    }
}