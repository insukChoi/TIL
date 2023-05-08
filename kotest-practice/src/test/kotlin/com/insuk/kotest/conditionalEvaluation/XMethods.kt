package com.insuk.kotest.conditionalEvaluation

import io.kotest.core.spec.style.DescribeSpec

class XMethods : DescribeSpec({
    xdescribe("this block and it's children are now disabled") {
        it("will not run") {
            // disabled test
        }
    }
})

class XMethodsExample : DescribeSpec({
    describe("this block is enabled") {
        xit("will not run") {
            // disabled test
        }
        it("will run") {
            // enabled test
        }
    }

})