package com.insuk.kotest.testStyles

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class StringSpecTest : StringSpec({
//    "stings.length should return size of string" {
//        "hello".length shouldBe 5
//    }

    "strings.length should return size of string".config(
        enabled = false, // Disabled by enabled flag in config
        invocations = 3
    ) {
        "hello".length shouldBe 5
    }
})