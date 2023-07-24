package com.example.atomickotlin.companionObjects

import com.example.atomickotlin.atomicTest.eq

class WithNamed {
    companion object Named {
        fun s() = "from Named"
    }
}

class WithDefault {
    companion object {
        fun s() = "from Default"
    }
}

fun main() {
    WithNamed.s() eq "from Named"
    WithNamed.Named.s() eq "from Named"
    WithDefault.s() eq "from Default"
    // 디폴트 이름을 'Companion' 이다
    WithDefault.Companion.s() eq "from Default"
}