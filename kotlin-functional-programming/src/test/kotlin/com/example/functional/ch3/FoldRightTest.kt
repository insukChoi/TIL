package com.example.functional.ch3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FoldRightTest {

    @Test
    fun sum2Test() {
        val sum2 = sum2(Cons(1, Cons(2, Cons(3, Nil))))
        assertThat(sum2).isEqualTo(6)
    }

    @Test
    fun product2Test() {
        val product = product2(Cons(2.0, Cons(3.0, Nil)))
        assertThat(product).isEqualTo(6.0)
    }

    @Test
    fun `foldRight를 사용해 리스트 길이를 계산하라`() {
        val length = length(Cons(1, Cons(2, Cons(3, Nil))))
        assertThat(length).isEqualTo(3)
    }
}