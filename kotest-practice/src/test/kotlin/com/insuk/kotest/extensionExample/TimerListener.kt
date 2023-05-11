package com.insuk.kotest.extensionExample

import io.kotest.core.listeners.AfterTestListener
import io.kotest.core.listeners.BeforeTestListener
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult

object TimerListener : BeforeTestListener, AfterTestListener {

    private var started = 0L

    override suspend fun beforeTest(testCase: TestCase) {
        started = System.currentTimeMillis()
    }

    override suspend fun afterTest(testCase: TestCase, result: TestResult) {
        println("Duration of ${testCase.descriptor} = " + (System.currentTimeMillis() - started))
    }
}