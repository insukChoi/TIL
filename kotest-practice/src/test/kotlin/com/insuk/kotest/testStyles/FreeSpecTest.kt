package com.insuk.kotest.testStyles

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class FreeSpecTest : FreeSpec({
    "String.length" - {
        "should return the length of the string" {
            "sammy".length shouldBe 5
            "".length shouldBe 0
        }
    }

    "containers can be nested as depp as you want" - {
        "and so we nest another container" - {
            "yet another container" - {
                "finally a real test" {
                    1 + 1 shouldBe 2
                }
            }
        }
    }
})

// caution : The innermost test must not use the - (minus) keyword after the test name.