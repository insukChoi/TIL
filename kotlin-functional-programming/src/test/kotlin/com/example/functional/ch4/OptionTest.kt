package com.example.functional.ch4

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OptionTest {
    @Test
    fun `mean test - empty list`() {
        val option = mean(listOf())
        assertThat(option).isEqualTo(None)
    }


    @Test
    fun `mean test - data`() {
        val option = mean(listOf(1.0, 2.0, 3.0))
        assertThat(option).isEqualTo(Some(2.0))
    }
}