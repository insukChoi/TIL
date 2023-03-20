package com.example.junit5referencepractice

import com.example.junit5referencepractice.RandomParametersExtension.Random
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(RandomParametersExtension::class)
class MyRandomParameterTest {
    @Test
    fun injectsInteger(@Random i: Int, @Random j: Int) {
        assertNotEquals(i, j)
    }

    @Test
    fun injectsDouble(@Random d: Double) {
        assertEquals(0.0, d, 1.0)
    }
}