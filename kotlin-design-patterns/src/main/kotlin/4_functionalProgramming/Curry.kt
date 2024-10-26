package `4_functionalProgramming`

/**
 * 커리(Curry)는 인수 여러 개를 받는 함수를 단일 인수를 받는 함수의 연쇄 호출로 변환하는 것을 말한다.
 */

//fun subtract(x: Int, y: Int): Int {
//    return x - y
//}
//fun main() {
//    println(subtract(50, 8))
//}

fun subtract(x: Int): (Int) -> Int = { y: Int -> x - y }

fun main() {
    println(subtract(50)(8))

    val infoLogger = createLogger(LogLevel.INFO)
    infoLogger("로그 메시지")
}

/*
 * 실제 코드에서는 로그를 기록할 때 커리를 사용하기도 한다.
 */
enum class LogLevel {
    ERROR, WARNING, INFO
}
fun log(level: LogLevel, message: String) = println("$level: $message")

fun createLogger(level: LogLevel) : (String) -> Unit {
    return {
        message: String -> log(level, message)
    }
}
