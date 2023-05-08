package com.insuk.kotest.conditionalEvaluation

import io.kotest.core.spec.style.StringSpec

class EnabledFlags : StringSpec({
    "should do something".config(enabled = false) {
        // test here
    }
})