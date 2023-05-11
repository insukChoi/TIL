package com.insuk.kotest.lifestyleHooks

import io.kotest.assertions.fail
import io.kotest.core.spec.BeforeTest
import io.kotest.core.spec.style.WordSpec

val startTest: BeforeTest = {
    println("Starting a test $it")
}

class TestSpec : WordSpec({
    // used once
    beforeTest(startTest)

    "this test" should {
        "be alive" {
            println("Jonny5 is alive!")
        }
    }
})

class OtherSpec : WordSpec({
    // used twice
    beforeTest(startTest)

    "this test" should {
        "fail" {
            fail("boom")
        }
    }
})