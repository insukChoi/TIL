package com.example.junit5referencepractice

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestInterfaceDemo :
    TestLifeCycleLogger,
    TimeExecutionLogger,
    TestInterfaceDynamicTestsDemo
{
    @Test
    fun isEqualsValue() {
        Assertions.assertEquals(1, "a".length, "is always equals")
    }
}