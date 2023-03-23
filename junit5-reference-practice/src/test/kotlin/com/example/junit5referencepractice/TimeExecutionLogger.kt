package com.example.junit5referencepractice

import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TimingExtension::class)
interface TimeExecutionLogger {

}