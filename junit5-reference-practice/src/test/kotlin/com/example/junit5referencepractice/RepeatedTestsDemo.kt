package com.example.junit5referencepractice

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.RepetitionInfo
import org.junit.jupiter.api.TestInfo
import java.util.logging.Logger

class RepeatedTestsDemo {
    val logger: Logger
        get() = Logger.getLogger(TestLifeCycleLogger::class.java.simpleName)

    @BeforeEach
    fun beforeEach(testInfo: TestInfo, repetitionInfo: RepetitionInfo) {
        val currentRepetition = repetitionInfo.currentRepetition
        val totalRepetitions = repetitionInfo.totalRepetitions

        val methodName = testInfo.testMethod.get().name
        logger.info("About to execute repetition $currentRepetition " +
                "of $totalRepetitions for $methodName")
    }

    @RepeatedTest(10)
    fun repeatedTest() {}

    @RepeatedTest(5)
    fun repeatedTestWithRepetitionInfo(repetitionInfo: RepetitionInfo) {
        assertEquals(5, repetitionInfo.totalRepetitions)
    }

    @RepeatedTest(value = 1, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("Repeat!")
    fun customDisplayName(testInfo: TestInfo) {
        assertEquals("Repeat! 1/1", testInfo.displayName)
    }

    @RepeatedTest(value = 1, name = RepeatedTest.LONG_DISPLAY_NAME)
    @DisplayName("Details...")
    fun customDisplayNameWithLongPattern(testInfo: TestInfo) {
        assertEquals("Details... :: repetition 1 of 1", testInfo.displayName)
    }

    @RepeatedTest(value = 5, name = "Wiederholung {currentRepetition} von {totalRepetitions}")
    fun repeatedTestInGerman() {
        
    }
}