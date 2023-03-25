package com.example.junit5referencepractice

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ExternalMethodSourceDemo {
    @ParameterizedTest
    @MethodSource("com.example.junit5referencepractice.StringsProviders#tinyStrings")
    fun testWithExternalMethodSource(tinyString: String) {

    }
}

class StringsProviders {
    companion object {
        @JvmStatic
        fun tinyStrings(): Stream<String> {
            return Stream.of(".", "oo", "000")
        }
    }
}