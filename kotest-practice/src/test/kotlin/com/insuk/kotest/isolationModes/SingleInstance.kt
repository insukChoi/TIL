package com.insuk.kotest.isolationModes

import io.kotest.core.spec.style.WordSpec
import java.util.*

class SingleInstance : WordSpec({
    val id = UUID.randomUUID()
    "a" should {
        println(id)
        "b" {
            println(id)
        }
        "c" {
            println(id)
        }
    }
})