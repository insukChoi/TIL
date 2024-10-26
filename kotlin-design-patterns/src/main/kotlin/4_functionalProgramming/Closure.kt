package `4_functionalProgramming`

/**
 * 객체지향 패러다임에서는 상태를 항상 객체에 저장한다. 그러나 함수형 프로그래밍에서는 꼭 그렇지 않다.
 */
fun counter(): () -> Int {
    var i = 0
    return { i++ }
}

fun main() {
    val next = counter()
    println(next()) // 0
    println(next()) // 1
    println(next()) // 2
}

// 호출할 때마다 1씩 증가하는 값이 함수의 상태다. 객체를 사용하지 않고도 상태를 표현할 수 잇는 것이다.
// 이를 클로저(closure) 라고 한다. 이 람다 함수에 대한 참조를 들고 있는 동안은 이 지역 변수의 상태도 유지된다.