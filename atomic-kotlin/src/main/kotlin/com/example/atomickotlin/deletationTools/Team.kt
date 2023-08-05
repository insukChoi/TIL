package com.example.atomickotlin.deletationTools

import com.example.atomickotlin.atomicTest.eq
import kotlin.properties.Delegates

class Team {
    var msg = ""
    var captain: String by Delegates.observable("<0>") {
        property, oldValue, newValue ->
        msg += "${property.name} $oldValue to $newValue "
    }
}

fun main() {
    val team = Team()
    team.captain = "Adam"
    team.captain = "Amanda"
    team.msg eq "captain <0> to Adam captain Adam to Amanda"
}