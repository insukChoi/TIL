plugins {
    kotlin("jvm") version "1.7.21"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-core")
    implementation("org.slf4j:slf4j-api")
    implementation("org.slf4j:slf4j-simple")

    implementation("org.springframework.integration:spring-integration-core")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

dependencyManagement {
    dependencies {
        // 아래 둘 중 하나의 방법으로 dependency 를 정의할 수 있다.
        dependency("org.springframework:spring-core:4.0.3.RELEASE")
//        dependency(mapOf(
//            "group" to "org.springframework",
//            "name" to "spring-core",
//            "version" to "4.0.3.RELEASE"
//        ))

        // 같은 그룹과 버전일 경우, 아래와 같이 set 으로 묶어서 정의할 수 있다.
        dependencySet("org.slf4j:1.7.7") {
            entry("slf4j-apo")
            entry("slf4j-simple")
        }

        // Exclusions
        dependency("org.springframework:spring-core:4.0.3.RELEASE") {
            exclude("commons-logging:commons-logging")
        }

        // Importing a Maven Bom
        // show ./gradlew dependencies --configuration compileClasspath
        imports {
            mavenBom("io.spring.platform:platform-bom:1.0.1.RELEASE")
        }
    }
}