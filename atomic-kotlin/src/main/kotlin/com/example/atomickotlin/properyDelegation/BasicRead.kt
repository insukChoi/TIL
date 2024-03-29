package com.example.atomickotlin.properyDelegation

import com.example.atomickotlin.atomicTest.eq
import kotlin.reflect.KProperty

class Readable(val i: Int) {
    val value: String by BasicRead()
}

class BasicRead {
    operator fun getValue(
        r: Readable,
        properties: KProperty<*>
    ) = "getValue: ${r.i}"
}

fun main() {
    val x = Readable(11)
    val y = Readable(17)
    x.value eq "getValue: 11"
    y.value eq "getValue: 17"
}
