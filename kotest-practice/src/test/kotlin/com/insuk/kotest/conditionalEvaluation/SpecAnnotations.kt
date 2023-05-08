package com.insuk.kotest.conditionalEvaluation

import io.kotest.core.annotation.EnabledCondition
import io.kotest.core.annotation.EnabledIf
import io.kotest.core.annotation.Ignored
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.spec.style.FunSpec
import kotlin.reflect.KClass

@Ignored
class IgnoredSpec : FunSpec() {
    init {
        error("boom")
    }
}

class LinuxOnlyCondition : EnabledCondition {
    override fun enabled(kclass: KClass<out Spec>): Boolean =
        when {
            kclass.simpleName?.contains("Linux") == true -> true
            else -> false
        }
}

@EnabledIf(LinuxOnlyCondition::class)
class MyLinuxTest1 : FunSpec() {
    // tests here
}

@EnabledIf(LinuxOnlyCondition::class)
class MyLinuxTest2 : DescribeSpec() {
    // tests here
}

@EnabledIf(LinuxOnlyCondition::class)
class MyWindowsTests : DescribeSpec() {
    // tests here
}