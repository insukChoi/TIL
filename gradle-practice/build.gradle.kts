import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.custom.InsukTask

// https://plugins.gradle.org/
plugins {
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.0"

    // https://kotlinlang.org/docs/gradle.html#apply-the-plugin
    kotlin("jvm") version "1.7.21"
    kotlin("plugin.spring") version "1.7.21"
}

group = "com.insuk"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

println("Configuration(구성) phase 입니다.")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val hello by tasks.registering {
    doLast {
        println("hello")
    }
}

val copy by tasks.registering(Copy::class) {
    from(file("srcDir"))
    into(buildDir)
}

abstract class CustomTask @Inject constructor(
    private val message: String,
    private val number: Int
) : DefaultTask()

tasks.register<CustomTask>("myTask", "hello", 42)

allprojects {
    // Set a default value
    extra["hasIntegrationTests"] = false

    afterEvaluate {
        if (extra["hasIntegrationTests"] as Boolean) {
            println("Adding integrationTest task to $project")
            tasks.register("integrationTest") {
                doLast {
                    println("Running integrationTest for $project")
                }
            }
        }
    }
}

// Developing Custom Gradle Task Types
abstract class GreetingTask : DefaultTask() {
    @get:Input
    abstract val greeting: Property<String>

    init {
        greeting.convention("default from GreetingTask")
    }

    @TaskAction
    fun greet() {
        println(greeting.get())
    }
}

tasks.register<GreetingTask>("greetTaskDefault")
tasks.register<GreetingTask>("greetTask") {
    greeting.set("greeting from GreetingTask")
}

// buildSrc custom Task
tasks.register<InsukTask>("insukCustomTask") {
    greeting.set("hoho")
}


interface GreetingPluginExtension {
    val message : Property<String>
    val greeter: Property<String>
}

class GreetingPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        // Add the 'greeting' extension object
        val extension = project.extensions.create<GreetingPluginExtension>("greeting")
        // Add a task that uses configuration from the extension object
        project.task("greeting") {
            doLast {
                println("${extension.message.get()} from ${extension.greeter.get()}")
            }
        }
    }
}
// Apply the plugin
apply<GreetingPlugin>()

// Configuration the extension
configure<GreetingPluginExtension> {
    message.set("Hi")
    greeter.set("Gradle")
}