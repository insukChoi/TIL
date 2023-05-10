package com.insuk.kotest.isolationModes

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.WordSpec
import java.util.concurrent.atomic.AtomicInteger

class InstancePerLeaf : WordSpec() {
    override fun isolationMode(): IsolationMode = IsolationMode.InstancePerLeaf

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

class InstancePerLeafExample : WordSpec() {
    override fun isolationMode(): IsolationMode = IsolationMode.InstancePerLeaf

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