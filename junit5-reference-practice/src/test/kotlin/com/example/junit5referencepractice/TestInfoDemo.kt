package com.example.junit5referencepractice

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo

/**
 * TestInfoParameterResolver: 생성자 또는 메소드 매개변수가 유형인 경우 현재 컨테이너 또는 테스트에 해당하는 인스턴스를 매개 변수 값으로 TestInfo 를 제공.
 */
@DisplayName("TestInfo Demo")
class TestInfoDemo {

    @BeforeEach
    fun init(testInfo: TestInfo) {
        val displayName = testInfo.displayName
        assertTrue(displayName.equals("TEST 1") || displayName.equals("test2()"))
    }

    @Test
    @DisplayName("TEST 1")
    @Tag("my-tag")
    fun test1(testInfo: TestInfo) {
        assertEquals("TEST 1", testInfo.displayName)
        assertTrue(testInfo.tags.contains("my-tag"))
    }

    @Test
    fun test2() {

    }
}