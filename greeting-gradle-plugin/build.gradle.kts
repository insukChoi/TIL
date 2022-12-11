plugins {
    kotlin("jvm") version "1.7.21"
    `java-gradle-plugin`
    `maven-publish`
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("simplePlugin") { // java-gradle-plugin 을 활용하여 플러그인 생성
            id = "org.example.greeting"
            implementationClass = "org.example.GreetingPlugin" // 구현체
        }
    }
}