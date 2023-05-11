package com.insuk.kotest.lifestyleHooks

import io.kotest.core.spec.style.WordSpec

class DslMethods : WordSpec({
    beforeTest {
        println("Starting a test $it")
    }
    afterTest { (test, result) ->
        println("Finished spec with result $result")
    }
    "this test" should {
        "be alive" {
            println("Jonny5 is alive!")
        }
    }
})