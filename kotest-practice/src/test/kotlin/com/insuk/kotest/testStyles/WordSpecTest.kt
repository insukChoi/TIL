package com.insuk.kotest.testStyles

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

class WordSpecTest : WordSpec({
    "String.length" should {
        "return the length of the string" {
            "sammy".length shouldBe 5
            "".length shouldBe 0
        }
    }

    "Hello" `when` {
        "asked for length" shouldBe {
            "sammy".length shouldBe 5
            "".length shouldBe 0
        }
    }
})