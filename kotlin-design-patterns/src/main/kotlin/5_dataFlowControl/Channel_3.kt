package `5_dataFlowControl`

import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.coroutineScope

/**
 * 행위자 코루틴 : actor() 함수는 produce() 함수와 마찬가지로 내부에 채널을 갖고 있는 코루틴을 생성한다.
 * 다만, 생산자가 채널에 값을 제공하는 역할을 했다면, 행위자는 채널에서 값을 가져오는 일을 한다.
 */
@OptIn(ObsoleteCoroutinesApi::class)
suspend fun main(): Unit = coroutineScope {
    val actor = actor<Int> {
        channel.consumeEach {
            println(it)
        }
    }

    (1..100).forEach {
        actor.send(it)
    }
}