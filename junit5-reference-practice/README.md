# [Junit5 Reference](https://junit.org/junit5/docs/current/user-guide/#overview)

## What is Junit 5?
JUnit 5 is composed of several different modules from three different sub-projects. <br>
**JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage**

- JUnit Platform
  - Unit 플랫폼은 JVM에서 테스트 프레임워크를 시작하기 위한 기반 역할
  - 플랫폼에서 실행되는 테스트 프레임워크를 개발하기 위한 TestEngine API를 정의
  - 하나 이상의 테스트 엔진을 사용하여 사용자 지정 테스트 스위트를 실행하기 위한 명령줄 및 JUnit 플랫폼 스위트 엔진에서 플랫폼을 시작하기 위한 Console Launcher를 제공
  
- JUnit Jupiter
    - JUnit 5에서 테스트 및 extension 을 작성하기 위한 프로그래밍 모델과 확장 모델의 조합
    - Jupiter 하위 프로젝트는 플랫폼에서 Jupiter 기반 테스트를 실행하기 위한 TestEngine을 제공

- JUnit Vintage
    - JUnit 3 및 JUnit 4 기반 테스트를 실행하기 위한 TestEngine을 제공

![junit5](https://user-images.githubusercontent.com/14847562/216350677-245aa19e-233a-48d9-ac72-79d9e8d35f8c.png)


## Supported Java Versions
JUnit 5 requires Java 8 (or higher) at runtime. However, you can still test code that has been compiled with previous versions of the JDK.