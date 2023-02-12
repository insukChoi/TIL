package com.example.junit5referencepractice

import org.junit.jupiter.api.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DisplayNameGeneratorDemo {
    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
    internal class A_year_is_not_supported {
        @Test
        fun if_it_is_zero() {
        }

        @DisplayName("A negative value for year is not supported by the leap year computation.")
        @ParameterizedTest(name = "For example, year {0} is not supported.")
        @ValueSource(ints = [-1, -4])
        fun if_it_is_negative(year: Int) {
        }
    }

    @Nested
    @IndicativeSentencesGeneration(separator = " -> ", generator = DisplayNameGenerator.ReplaceUnderscores::class)
    internal class A_year_is_a_leap_year {
        @Test
        fun if_it_is_divisible_by_4_but_not_by_100() {
        }

        @ParameterizedTest(name = "Year {0} is a leap year.")
        @ValueSource(ints = [2016, 2020, 2048])
        fun if_it_is_one_of_the_following_years(year: Int) {
        }
    }
}