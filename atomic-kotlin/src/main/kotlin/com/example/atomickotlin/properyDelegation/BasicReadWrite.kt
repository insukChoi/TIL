package com.example.atomickotlin.properyDelegation

import com.example.atomickotlin.atomicTest.eq
import kotlin.reflect.KProperty

class ReadWriteable(var i: Int) {
    var msg = ""
    var value: String by BasicReadWrite()
}

class BasicReadWrite {
    operator fun getValue(
        rw: ReadWriteable,
        properties: KProperty<*>
    ) = "getValue: ${rw.i}"

    operator fun setValue(
        rw: ReadWriteable,
        properties: KProperty<*>,
        s: String
    ) {
        rw.i = s.toIntOrNull() ?: 0
        rw.msg = "setValue to ${rw.i}"
    }
}

fun main() {
    val x = ReadWriteable(11)
    x.value eq "getValue: 11"
    x.value = "99"
    x.msg eq "setValue to 99"
    x.value eq "getValue: 99"
}