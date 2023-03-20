package com.example.junit5referencepractice

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolutionException
import org.junit.jupiter.api.extension.ParameterResolver
import java.lang.reflect.Parameter


class RandomParametersExtension : ParameterResolver {
    @Retention
    @Target(AnnotationTarget.VALUE_PARAMETER)
    annotation class Random

    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext?): Boolean =
        parameterContext.isAnnotated(Random::class.java)

    override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext)
        = getRandomValue(parameterContext.parameter, extensionContext)

    private fun getRandomValue(parameter: Parameter, extensionContext: ExtensionContext): Any {
        val type: Class<*> = parameter.type
        val random: java.util.Random = extensionContext.root.getStore(ExtensionContext.Namespace.GLOBAL)
            .getOrComputeIfAbsent(java.util.Random::class.java)
        if (Int::class.javaPrimitiveType == type) {
            return random.nextInt()
        }
        if (Double::class.javaPrimitiveType == type) {
            return random.nextDouble() }

        throw ParameterResolutionException("No random generator implemented for $type")
    }
}