package com.insuk.kotest.testStyles

import io.kotest.core.spec.style.BehaviorSpec

class BehaviorSpecTest : BehaviorSpec({
    given("a broomstick") {
        `when`("I sit on it") {
            then("I should be able to fly") {
                // test code
            }
        }
        `when`("I throw it away") {
            then("it should come back") {
                // test code
            }
        }
    }

    given("a broomstick2") {
        and("a witch") {
            `when`("Then witch sits on it") {
                and("she laughs hysterically") {
                    then("She should be able to fly") {
                        // test code
                    }
                }
            }
        }
    }
})