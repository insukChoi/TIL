package `5_dataFlowControl`

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * 생산자 코루틴 : 채널에 값을 공급하는 코루틴이 필요하다면 produce() 함수를 사용해 생산자 코루틴을 만들 수 있다.
 * produce() 내부적으로 ReceiveChannel<T> 를 갖고 있는 코루틴을 생성한다.
 */
@OptIn(ExperimentalCoroutinesApi::class)
suspend fun main(): Unit = coroutineScope {
    val channel = produce {
        (1..10).forEach {
            send(it)
        }
    }

    launch {
        channel.consumeEach {
            println(it)
        }
    }
}