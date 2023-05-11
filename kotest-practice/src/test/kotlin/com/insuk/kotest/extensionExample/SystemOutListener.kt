package com.insuk.kotest.extensionExample

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.system.NoSystemOutListener

class SystemOutListener : DescribeSpec({
    listeners(NoSystemOutListener)

    describe("All these tests should not write to standard out") {
        it("silence in the court") {
            println("boom") // failure
        }
    }
})