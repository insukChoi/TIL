package com.insuk.kotest.testStyles

import io.kotest.core.spec.style.DescribeSpec

class DescribeSpecTest : DescribeSpec({
    describe("score") {
        it("start as zero") {
            // test here
        }
        describe("with a strike") {
            it("adds ten") {
                // test here
            }
            it("carries strike to the next frame") {
                // test here
            }
        }
        describe("fro the opposite team") {
            it("Should negate one score") {
                // test here
            }
        }
    }
})