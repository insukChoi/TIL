package com.insuk.kotest.testStyles

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class ShouldSpecTest : ShouldSpec({
    should("return the length of the string") {
        "sammy".length shouldBe 5
        "".length shouldBe 0
    }

    // nested in one or more context blocks
    context("String.length") {
        should("return the length of the string") {
            "sammy".length shouldBe 5
            "".length shouldBe 0
        }
    }

    context("this outer block is enabled") {
        xshould("this test is disabled") {
            // test here
        }
    }

    xcontext("this block is disabled") {
        should("disabled by inheritance from the parent") {
            // test here
        }
    }
})