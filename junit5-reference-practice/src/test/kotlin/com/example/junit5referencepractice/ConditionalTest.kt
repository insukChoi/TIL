package com.example.junit5referencepractice

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledOnOs
import org.junit.jupiter.api.condition.EnabledOnOs
import org.junit.jupiter.api.condition.OS

class ConditionalTest {
    @Test
    @EnabledOnOs(OS.MAC)
    fun onlyOnMacOs() {
        // ...
    }

    @TestOnMac
    fun testOnMac() {
        // ...
    }

    @Test
    @EnabledOnOs(*[OS.WINDOWS, OS.MAC])
    fun onLinuxOrMac() {
        // ...
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    fun notOnWindows() {
        // ...
    }

    @Target(AnnotationTarget.FUNCTION)
    @Retention
    @Test
    @EnabledOnOs(OS.MAC)
    internal annotation class TestOnMac
}