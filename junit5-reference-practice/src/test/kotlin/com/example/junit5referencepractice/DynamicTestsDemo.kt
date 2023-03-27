package com.example.junit5referencepractice

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.function.ThrowingConsumer
import org.junit.platform.commons.util.StringUtils
import java.util.*
import java.util.stream.Stream

open class DynamicTestsDemo {
    private val calculator = Calculator()

    @TestFactory
    fun dynamicTestsFromCollection(): List<Any> {
        return listOf(
            dynamicTest("1st dynamic test") {
                assertTrue(StringUtils.isNotBlank("madam"))
            },
            dynamicTest("2nd dynamic test") {
                assertEquals(4, calculator.multiply(2, 2))
            }
        )
    }

    @TestFactory
    fun dynamicTestsFromStream(): Stream<DynamicTest> {
        return Stream.of("racecar", "radar", "mom", "dad")
            .map { text -> dynamicTest(text) { assertTrue(StringUtils.isNotBlank(text)) } }
    }


    @TestFactory
    fun generateRandomNumberOfTestsFromIterator(): Stream<DynamicTest?>? {

        // Generates random positive integers between 0 and 100 until
        // a number evenly divisible by 7 is encountered.
        val inputGenerator: MutableIterator<Int?> = object : MutableIterator<Int?> {
            var random: Random = Random()
            var current = 0
            override fun hasNext(): Boolean {
                current = random.nextInt(100)
                return current % 7 != 0
            }

            override fun next(): Int {
                return current
            }

            override fun remove() {
                TODO("Not yet implemented")
            }
        }

        // Generates display names like: input:5, input:37, input:85, etc.
        val displayNameGenerator: (Int?) -> String = { input: Int? -> "input:$input" }

        // Executes tests based on the current input value.
        val testExecutor: ThrowingConsumer<Int?> = ThrowingConsumer<Int?> { input -> assertTrue(input % 7 != 0) }

        // Returns a stream of dynamic tests.
        return DynamicTest.stream(inputGenerator, displayNameGenerator, testExecutor)
    }
}