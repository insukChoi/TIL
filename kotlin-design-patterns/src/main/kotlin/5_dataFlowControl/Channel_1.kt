package `5_dataFlowControl`

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 이런 통신 형태를 순차 프로세스 통신(Communicating Sequential Processes 또는 줄여서 CSP 라고 부른다)
 */
fun main(): Unit = runBlocking {
    val channel = Channel<Int>()

    launch {
        for (c in channel) {
            println("f = $c")
        }
    }

    launch {
        for (c in channel) {
            println("s = $c")
        }
    }

    (1..10).forEach {
        channel.send(it)
    }

    channel.close()
}
/*
채널은 데이터 핫 스트림이다.
f = 1
f = 2
f = 4
s = 3
f = 5
f = 7
s = 6
f = 8
f = 10
s = 9
 */