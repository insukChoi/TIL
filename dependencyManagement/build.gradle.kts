import groovy.util.Node

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
    implementation("com.google.guava:guava")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

//ext["spring.version"] = "4.0.4.RELEASE"

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
            mavenBom("io.spring.platform:platform-bom:1.0.1.RELEASE") {
                // Overriding Versions in a Bom
//                bomProperty("spring.version", "4.0.4.RELEASE")
//                bomProperties(mapOf(
//                    "spring.version" to "4.0.4.RELEASE"
//                ))
            }
            dependencies {
                dependency("com.google.guava:guava:18.0")
            }
        }

        overriddenByDependencies(false)

        // Accessing propeties from Imported Boms
        println(dependencyManagement.importedProperties["spring.version"])

        // Programmatic access
        val managedVersions = dependencyManagement.managedVersions
        println(managedVersions)
        println(dependencyManagement.getManagedVersionsForConfiguration(configurations.getByName("implementation")))

        val springCoreVersion = managedVersions["org.springframework:spring-core"]
        println(springCoreVersion)
    }

    resolutionStrategy {
        // 플러그인은 내부 종속성 해결을 위해 별도의 분리된 구성을 사용합니다.
        // 클로저를 사용하여 이러한 구성에 대한 해결 전략을 구성할 수 있습니다.
        // 스냅샷을 사용하는 경우 변경 모듈을 0초 동안 캐시하도록 Gradle을 구성하여 가져온 bom의 캐싱을 비활성화할 수 있습니다.
        cacheChangingModulesFor(0, TimeUnit.SECONDS)
    }
}