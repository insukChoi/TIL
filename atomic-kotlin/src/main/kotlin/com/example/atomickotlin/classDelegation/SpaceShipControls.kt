package com.example.atomickotlin.classDelegation

import com.example.atomickotlin.atomicTest.eq

interface Controls {
    fun up(velocity: Int): String
    fun down(velocity: Int): String
    fun left(velocity: Int): String
    fun right(velocity: Int): String
    fun forward(velocity: Int): String
    fun back(velocity: Int): String
    fun turboBoost(): String
}

class SpaceShipControls : Controls {
    override fun up(velocity: Int): String = "up $velocity"

    override fun down(velocity: Int): String = "down $velocity"

    override fun left(velocity: Int): String = "left $velocity"

    override fun right(velocity: Int): String = "right $velocity"

    override fun forward(velocity: Int): String = "forward $velocity"

    override fun back(velocity: Int): String = "back $velocity"

    override fun turboBoost(): String = "turbo boost"
}

// by 를 쓰지 않을 경우
class ExplicitControls : Controls {
    private val controls = SpaceShipControls()

    // 수동으로 위임 구현하기
    override fun up(velocity: Int): String = controls.up(velocity)

    override fun down(velocity: Int): String = controls.down(velocity)

    override fun left(velocity: Int): String = controls.left(velocity)

    override fun right(velocity: Int): String = controls.right(velocity)

    override fun forward(velocity: Int): String = controls.forward(velocity)

    override fun back(velocity: Int): String = controls.back(velocity)

    // 변형한 구현
    override fun turboBoost(): String = controls.turboBoost() + "... boooooost!"
}

// by 를 사용한 경우
class DelegatedControls(
    private val controls: SpaceShipControls = SpaceShipControls()
) : Controls by controls {
    override fun turboBoost(): String = "${controls.turboBoost()}... boooooost!"
}

fun main() {
    val controls = DelegatedControls()
    controls.forward(100) eq "forward 100"
    controls.turboBoost() eq "turbo boost... boooooost!"
}
