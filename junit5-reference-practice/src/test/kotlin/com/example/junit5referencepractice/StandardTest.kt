package com.example.junit5referencepractice

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assumptions.assumeTrue

class StandardTest {

    @BeforeEach
    fun init() {
    }

    @Test
    fun succeedingTest() {
    }

    @Test
    fun failingTest() {
        fail("a failing test")
    }

    @Test
    @Disabled("for demonstration purposes")
    fun skippedTest() {
        // not executed
    }

    @Test
    fun abortedTest() {
        assumeTrue("abc".contains("Z"))
        fail("test should have been aborted")
    }

    @AfterEach
    fun tearDown() {
    }

    companion object {
        @JvmStatic
        @BeforeAll
        fun initAll() {
        }

        @JvmStatic
        @AfterAll
        fun tearDownAll() {
        }
    }
}