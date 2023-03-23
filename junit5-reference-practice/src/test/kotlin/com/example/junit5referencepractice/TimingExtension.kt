package com.example.junit5referencepractice

import org.junit.jupiter.api.extension.AfterTestExecutionCallback
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ExtensionContext.Namespace
import org.junit.jupiter.api.extension.ExtensionContext.Store
import java.lang.reflect.Method
import java.util.logging.Logger

class TimingExtension : BeforeTestExecutionCallback, AfterTestExecutionCallback {
    @Throws(Exception::class)
    override fun beforeTestExecution(context: ExtensionContext) {
        getStore(context).put(START_TIME, System.currentTimeMillis())
    }

    @Throws(Exception::class)
    override fun afterTestExecution(context: ExtensionContext) {
        val testMethod: Method = context.requiredTestMethod
        val startTime: Long = getStore(context).remove(START_TIME, Long::class.javaPrimitiveType)
        val duration = System.currentTimeMillis() - startTime
        logger.info {
            String.format(
                "Method [%s] took %s ms.",
                testMethod.name,
                duration
            )
        }
    }

    private fun getStore(context: ExtensionContext): Store {
        return context.getStore(Namespace.create(javaClass, context.requiredTestMethod))
    }

    companion object {
        private val logger: Logger = Logger.getLogger(TimingExtension::class.java.name)
        private const val START_TIME = "start time"
    }
}