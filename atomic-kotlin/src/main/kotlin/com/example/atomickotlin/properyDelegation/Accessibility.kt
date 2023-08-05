package com.example.atomickotlin.properyDelegation

import com.example.atomickotlin.atomicTest.eq
import kotlin.properties.ReadOnlyProperty

class Person(
    private val first: String,
    private val last: String
) {
    // 위임자 객체의 private 멤버에 대한 접근을 가능하게 하려면 위임 클래스를 내포시켜야 한다.
    val name by ReadOnlyProperty<Person, String> { _, _ -> "$first $last"} // SAM 변환
}

fun main() {
    val alien = Person("Floopy", "Noopers")
    alien.name eq "Floopy Noopers"
}