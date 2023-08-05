package com.example.atomickotlin.deletationTools

import com.example.atomickotlin.atomicTest.eq

class Driver(
    map: MutableMap<String, Any?>
) {
    var name: String by map
    var age: Int by map
    var id: String by map
    var available: Boolean by map
    var coord: Pair<Double, Double> by map
}

fun main() {
    val info = mutableMapOf<String, Any?>(
        "name" to "Bruno Fiat",
        "age" to 22,
        "id" to "X97C111",
        "available" to false,
        "coord" to Pair(111.93, 12313.12)
    )

    val driver = Driver(info)
    driver.available eq false
    driver.available = true

    // 원본 맵 info 도 변경된다
    info eq "{name=Bruno Fiat, age=22, id=X97C111, available=true, coord=(111.93, 12313.12)}"
}