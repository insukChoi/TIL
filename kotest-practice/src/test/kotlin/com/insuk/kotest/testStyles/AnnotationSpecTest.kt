package com.insuk.kotest.testStyles

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe

class AnnotationSpecTest : AnnotationSpec() {
    @BeforeEach
    fun beforeTest() {
        println("Before each test")
    }

    @Test
    fun test1() {
        1 shouldBe 1
    }

    @Test
    fun test2() {
        2 shouldBe 2
    }
}