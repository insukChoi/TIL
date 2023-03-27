package com.example.junit5referencepractice

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.*
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalUnit
import java.util.*
import java.util.stream.IntStream
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ParameterizedTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3])
    fun testWithValueSource(argument: Int) {
        assertTrue(argument in 1..3)
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = [" ", "  ", "\t", "\n"])
    fun nullEmptyAndBlankStrings(text: String?) {
        assertTrue(text == null || text.trim().isEmpty());
    }

    @ParameterizedTest
    @EnumSource(ChronoUnit::class)
    fun testWithEnumSource(unit: TemporalUnit) {
        assertNotNull(unit)
    }

    @ParameterizedTest
    @EnumSource
    fun testWithEnumSourceWithAutoDetection(unit: ChronoUnit) {
        assertNotNull(unit)
    }

    @ParameterizedTest
    @EnumSource(names = ["DAYS", "HOURS"])
    fun testWithEnumSourceInclude(unit: ChronoUnit) {
        assertTrue(EnumSet.of(ChronoUnit.DAYS, ChronoUnit.HOURS).contains(unit))
    }

    @ParameterizedTest
    @EnumSource(mode = EnumSource.Mode.MATCH_ALL, names = ["^.*DAYS$"])
    fun testWithEnumSourceRegex(unit: ChronoUnit) {
        assertTrue(unit.name.endsWith("DAYS"))
    }

    @ParameterizedTest
    @MethodSource("stringProvider")
    fun testWithExplicitLocalMethodSource(argument: String) {
        assertNotNull(argument)
    }

    private fun stringProvider(): Stream<String> {
        return Stream.of("apple", "banana")
    }

    @ParameterizedTest
    @MethodSource
    fun testWithDefaultLocalMethodSource(argument: String) {
        assertNotNull(argument)
    }

    private fun testWithDefaultLocalMethodSource(): Stream<String> {
        return Stream.of("apple", "banana")
    }

    @ParameterizedTest
    @MethodSource("range")
    fun testWithRangeMethodSource(argument: Int) {
        assertNotEquals(9, argument)
    }

    private fun range(): IntStream {
        return IntStream.range(0, 20).skip(10)
    }

    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    fun testWithMultiArgMethodSource(str: String, num: Int, list: List<String>) {
        assertEquals(5, str.length)
        assertTrue(num in 1..2)
        assertEquals(2, list.size)
    }

    private fun stringIntAndListProvider(): Stream<Arguments> {
        return Stream.of(
            Arguments.arguments("apple", 1, listOf("a", "b")),
            Arguments.arguments("lemon", 2, listOf("x", "y"))
        )
    }

    @ParameterizedTest
    @CsvSource(
        quoteCharacter = '"', textBlock = """
          apple,         1
          banana,        2
          "lemon, lime", 0xF1
          strawberry,    700_000"""
    )
    fun test(fruit: String?, rank: Int) {
        assertNotNull(fruit)
        assertNotEquals(0, rank)
    }
}