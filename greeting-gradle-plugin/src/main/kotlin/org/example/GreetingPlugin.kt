package org.example

import org.gradle.api.Plugin
import org.gradle.api.Project

class GreetingPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("firstMessage") { // task 등록
            it.group = "my tasks" // task group명
            it.description = "This is my first task"
            it.doLast { // task 실행 block
                println("This is my first task!")
            }
        }

        project.tasks.register("secondMessage") { // task 등록
            it.group = "my tasks" // task group명
            it.description = "This is my second task"
            it.doLast { // task 실행 block
                println("This is my second task!")
            }
        }
    }
}