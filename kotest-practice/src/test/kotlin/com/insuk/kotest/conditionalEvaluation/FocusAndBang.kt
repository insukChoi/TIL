package com.insuk.kotest.conditionalEvaluation

import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.StringSpec

class FocusAndBang : StringSpec({
    "test 1" {
        // skipped
    }

    "f:test 2" {
        // focus 된 해당 테스트만 우선적으로 실행된다.
    }

    "test 3" {
        // skipped
    }
})

class FocusExample : FunSpec({
    context("test 1") {
        // this will be skipped
        test("foo") {
            // this will be skipped
        }
    }

    context("f:test 2") {
        // this will be executed
        test("foo") {
            // this will be executed
        }

        test("foo2") {
            // this will be executed
        }
    }

    context("test 3") {
        // this will be skipped
        test("foo") {
            // this will be skipped
        }
    }
})

class BangExample : StringSpec({
    "!test 1" {
        // this will be ignored
    }

    "test 2" {
        // this will run
    }

    "test 3" {
        // this will run too
    }
})