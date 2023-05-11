package com.insuk.kotest.lifestyleHooks

import io.kotest.core.spec.style.WordSpec
import io.kotest.core.test.TestCase

class OverridingCallbackFunc : WordSpec() {
    override suspend fun beforeTest(testCase: TestCase) {
        println("Starting a test $testCase")
    }

    init {
        "this test" should {
            "be alive" {
                println("Jonny5 is alive!")
            }
        }
    }
}