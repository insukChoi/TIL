package com.example.java;

import org.junit.jupiter.api.*;

public class StandardsTests {

    @BeforeAll
    static void initAll() {
        System.out.println("beforeAll");
    }

    @BeforeEach
    void init() {
        System.out.println("beforeEach");
    }

    @Test
    void test1() {
        System.out.println("----- Test 1 ------");
    }

    @Test
    void test2() {
        System.out.println("----- Test 2 ------");
    }

    @AfterEach
    void tearDown() {
        System.out.println("afterEach");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("afterAll");
    }
}
