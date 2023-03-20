import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl` // enable the Kotlin-DSL
    kotlin("jvm") version "1.8.20-RC"
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
    implementation(kotlin("stdlib-jdk8"))
}

repositories {
    mavenCentral()
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}