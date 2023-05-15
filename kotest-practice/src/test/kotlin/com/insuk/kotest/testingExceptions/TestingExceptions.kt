package com.insuk.kotest.testingExceptions

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.should
import io.kotest.matchers.string.startWith
import java.lang.IllegalArgumentException

val exception = shouldThrow<IllegalArgumentException> {
    throw IllegalArgumentException("Something went wrong")
}

class TestingExceptions : ShouldSpec({

    should("IllegalArgument Exception") {
        exception.message should startWith("Something went wrong")
    }
})