package com.example.junit5referencepractice

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.function.Executable
import org.junit.platform.commons.util.StringUtils
import java.util.stream.Stream

interface TestInterfaceDynamicTestsDemo {
    @TestFactory
    fun dynamicTestsForPalindromes(): Stream<DynamicTest> {
        return Stream.of("racecar", "radar", "mom", "dad")
            .map {
                dynamicTest(it) {
                    assertTrue(StringUtils.isNotBlank(it))
                }
            }
    }
}