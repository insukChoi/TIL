package com.example.atomickotlin.scopeFunctions

import com.example.atomickotlin.atomicTest.eq

/**
 * 영역 함수는 모두 Inline 함수이기에,
 * 컴파일러는 인라인 함수의 본문과 함께 람다 본문을 인라인 해준다
 */
data class Tag(var n: Int = 0) {
    var s: String = ""
    fun increment() = ++n
}

fun main() {
    // let(): 객체를 'it' 으로 접근하고
    // 람다의 마지막 식의 값을 반환한다
    Tag(1).let {
        it.s = "let: ${it.n}"
        it.increment()
    } eq 2

    // let() 을 사용하면서 람다 인자에 이름을 붙인 경우다
    Tag(2).let { tag ->
        tag.s = "let: ${tag.n}"
        tag.increment()
    } eq 3

    // run(): 객체를 'this' 로 접근하고
    // 람다의 마지막 식의 값을 반환한다.
    Tag(3).run {
        s = "run: $n"
        increment()
    } eq 4

    // with(): 객체를 'this' 로 접근하고
    // 람다의 마지막 식을 반환한다
    with(Tag(4)) {
        s = "with: $n"
        increment()
    } eq 5

    // apply(): 객체를 'this' 로 접근하고
    // 변경된 객체를 다시 반환한다
    Tag(5).apply {
        s = "apply: $n"
        increment()
    } eq "Tag(n=6)"

    // also(): 객체를 'it' 으로 접근하고
    // 변경된 객체를 다시 반환한다
    Tag(6).also {
        it.s = "also: ${it.n}"
        it.increment()
    } eq "Tag(n=7)"

    // also() 에서도 람다의 인자에 이름을 붙일 수 있다
    Tag(7).also { tag ->
        tag.s = "also: ${tag.n}"
        tag.increment()
    } eq "Tag(n=8)"
}