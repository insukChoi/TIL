package com.example.junit5referencepractice

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInfo
import org.junit.jupiter.api.TestInstance
import java.util.logging.Logger

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
interface TestLifeCycleLogger {
    val logger: Logger
        get() = Logger.getLogger(TestLifeCycleLogger::class.java.simpleName)

    @BeforeAll
    fun beforeAllTests() {
        logger.info("Before all tests")
    }

    @AfterAll
    fun afterAllTests() {
        logger.info("After all tests")
    }

    @BeforeEach
    fun beforeEachTest(testInfo: TestInfo) {
        logger.info("About to execute [${testInfo.displayName}]")
    }

    @AfterEach
    fun afterEachTest(testInfo: TestInfo) {
        logger.info("Finished executing [${testInfo.displayName}]")
    }
}