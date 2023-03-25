package com.example.junit5referencepractice

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import org.junit.jupiter.api.extension.RegisterExtension
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

/**
 * Factory methods can declare parameters, which will be provided by registered implementations of the ParameterResolver extension API.
 * In the following example, the factory method is referenced by its name since there is only one such method in the test class.
 * If there are several local methods with the same name, parameters can also be provided to differentiate them
 * – for example, @MethodSource("factoryMethod()") or @MethodSource("factoryMethod(java.lang.String)").
 *
 * Alternatively, the factory method can be referenced by its fully qualified method name,
 * e.g. @MethodSource("example.MyTests#factoryMethod(java.lang.String)").
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ParameterizedTestWithResolver {

    @ParameterizedTest
    @MethodSource("factoryMethodWithArguments")
    fun testWithFactoryMethodWithArguments(argument: String) {
        assertTrue(argument.startsWith("2"))
    }

    private fun factoryMethodWithArguments(quantity: Int): Stream<Arguments> {
        return Stream.of(
            Arguments.arguments("$quantity apple"),
            Arguments.arguments("$quantity lemons")
        )
    }

    companion object {
        @RegisterExtension
        val integerResolver: IntegerResolver = IntegerResolver()
    }

    class IntegerResolver: ParameterResolver {
        override fun supportsParameter(
            parameterContext: ParameterContext?,
            extensionContext: ExtensionContext?
        ): Boolean {
            return parameterContext!!.parameter.type == Int::class.java
        }

        override fun resolveParameter(parameterContext: ParameterContext?, extensionContext: ExtensionContext?): Any {
            return 2
        }
    }
}