package structuralPatterns

import java.util.stream.Stream

/**
 * 어댑터 패턴은 어떤 인터페이스를 다른 인터페이스로 변환하고자 할때 사용한다.
 */
fun main() {
    val list = listOf("a", "b", "c")

    println(list.stream()) // .stream() 이라는 어댑터가 존재
}

fun printStream(stream: Stream<String>) {
    stream.forEach(System.out::println)
}