package com.example.atomickotlin.inheritanceExtensions

import com.example.atomickotlin.atomicTest.eq

interface Device {
    val model: String
    val productionYear: Int
}

// 앞으로 overpriced() 와 outdated() 를 하위 클래스에서 오버라이드할 가능성이 없다고 가정하면 확장으로 처리할 수 있다.
fun Device.overPriced() = model.startsWith("i")

fun Device.outdated() = productionYear < 2050

class MyDevice(
    override val model: String,
    override val productionYear: Int
) : Device

fun main() {
    val gadget: Device = MyDevice("my first phone", 2000)

    gadget.outdated() eq true
    gadget.overPriced() eq false
}

/*
구체적인 특정 상황에서 상속을 어떻게 사용할지 심사숙고하는 중이라면, 진짜 상속이 필요한지 여부를 고려하고
'상속보다는 확장 함수와 합성을 택하라' 하는 격언을 적용하라.
 */
