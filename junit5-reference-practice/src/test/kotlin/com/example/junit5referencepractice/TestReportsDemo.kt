package com.example.junit5referencepractice

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestReporter

/**
 * 보고서 항목을 TestReporter 게시하거나 보고 인프라에 테스트할 수 있다.
 */
class TestReportsDemo {
    @Test
    fun reportSingleValue(testReporter: TestReporter) {
        testReporter.publishEntry("a status message")
    }

    @Test
    fun reportKeyValuePair(testReporter: TestReporter) {
        testReporter.publishEntry("a key", "a value")
    }

    @Test
    fun reportMultipleKeyValuePair(testReporter: TestReporter) {
        val values = mutableMapOf(
            "user name" to "dk38",
            "award year" to "1974"
        )

        testReporter.publishEntry(values)
    }
}