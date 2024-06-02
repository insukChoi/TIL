package com.example.functional.ch1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CafeTest {
    @Test
    fun test1() {
        val cafe: Cafe = Cafe().also {
            it.addFactory(Americano(), CafeLatte())
        }

        val myCard = object :CreditCard {}
        val americano = cafe.buy(myCard, 2, Americano::class)?.let { charge ->
            cafe.payment(charge)?.let { receipt ->
                cafe.receive(receipt)
            }
        }

        assertThat(americano?.size).isEqualTo(2)
        assertThat(americano?.get(0)?.price).isEqualTo(1000.0)
    }
}