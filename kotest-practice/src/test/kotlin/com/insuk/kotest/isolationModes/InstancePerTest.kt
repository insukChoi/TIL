package com.insuk.kotest.isolationModes

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.WordSpec
import java.util.concurrent.atomic.AtomicInteger

class InstancePerTest : WordSpec() {
    override fun isolationMode(): IsolationMode = IsolationMode.InstancePerTest

    init {
        "a" should {
            println("Hello")
            "b" {
                println("From")
            }
            "c" {
                println("Sam")
            }
        }
    }
}

class InstancePerTestExample : WordSpec() {
    override fun isolationMode(): IsolationMode = IsolationMode.InstancePerTest

    private val counter = AtomicInteger(0)

    init {
        "a" should {
            println("a=${counter.getAndIncrement()}")
            "b" {
                println("b=${counter.getAndIncrement()}")
            }
            "c" {
                println("c=${counter.getAndIncrement()}")
            }
        }
    }
}