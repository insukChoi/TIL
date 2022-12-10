# [Gradle Reference](https://docs.gradle.org/current/userguide/userguide.html)

> 그래들 래퍼런스를 정독하고, 연습해보자

## Build Lifecycle
Gradle core 는 의존성 기반 프로그래밍을 위한 언어이다.

Gradle 용어로 이것은 작업과 작업 간의 종속성을 정의할 수 있음을 의미합니다. Gradle은 이러한 작업이 종속성 순서대로 실행되고 각 작업이 한 번만 실행되도록 보장합니다.

빌드 단계

- **Initialization** : Gradle은 빌드에 참여할 프로젝트를 결정하고 프로젝트 인스턴스를 생성합니다.
- **Configuration** : 모든 프로젝트의 빌드 스크립트가 실행됩니다.
- **Execution :** 그래들은 커맨드라인에서 명령 된 task들을 실행한다.

[https://docs.gradle.org/current/userguide/build_lifecycle.html](https://docs.gradle.org/current/userguide/build_lifecycle.html)

## Developing Gradle Tasks
[https://docs.gradle.org/current/userguide/more_about_tasks.html](https://docs.gradle.org/current/userguide/more_about_tasks.html)

## Developing Custom Gradle Plugins
[https://docs.gradle.org/current/userguide/custom_plugins.html](https://docs.gradle.org/current/userguide/custom_plugins.html)