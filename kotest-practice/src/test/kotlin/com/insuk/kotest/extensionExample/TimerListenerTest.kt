package com.insuk.kotest.extensionExample

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class TimerListenerTest : FunSpec({
    extension(TimerListener)

    test("String length should return the length of the string") {
        "sammy".length shouldBe 5
        "".length shouldBe 0
    }
})