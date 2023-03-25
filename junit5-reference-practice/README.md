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

JUnit 5 requires Java 8 (or higher) at runtime. However, you can still test code that has been compiled with previous
versions of the JDK.

## Annotations

JUnit Jupiter supports the following annotations for configuring tests and extending the framework.

| JUint5 Annotation      | 내용                                                                                 | JUnit4 Annotation |
|------------------------|------------------------------------------------------------------------------------|-------------------|
| @Test                  | 	테스트 Method 임을 선언.	                                                                | @Test             |
| @ParameterizedTest     | 	매개변수를 받는 테스트를 작성할 수 있음.                                                           | 	                 |
| @RepeatedTest          | 	반복되는 테스트를 작성할 수 있음.                                                               | 	                 |
| @TestFactory           | 	@Test로 선언된 정적 테스트가 아닌 동적으로 테스트를 사용함.                                              | 	                 |
| @TestInstance          | 	테스트 클래스의 생명주기를 설정함.                                                               | 	                 |
| @TestTemplate          | 	providers 에 의해 여러 번 호출될 수 있도록 설계된 테스트 케이스 템플릿임을 나타냄.                              | 	                 |
| @TestMethodOrder       | 	테스트 메소드 실행 순서를 구성하는데 사용함.                                                         | 	                 |
| @DisplayName           | 	테스트 클래스 또는 메소드의 사용자 정의 이름을 선언할 때 사용함.                                             | 	                 |
| @DisplayNameGeneration | 	이름 생성기를 선언함. 예를 들어 '_'를 공백 문자로 치환해주는 생성기가 있음. ex ) new_test -> new test           | 	                 |
| @BeforeEach            | 	모든 테스트 실행 전에 실행할 테스트에 사용함.	                                                       | @Before           |
| @AfterEach             | 	모든 테스트 실행 후에 실행한 테스트에 사용함.                                                        | 	@After           |
| @BeforeAll             | 	현재 클래스를 실행하기 전 제일 먼저 실행할 테스트 작성하는데,  static로 선언함.	                                | @BeforeClass      |
| @AfterAll              | 	현재 클래스 종료 후 해당 테스트를 실행하는데,  static으로 선언함.                                         | 	@AfterClass      |
| @Nested                | 	클래스를 정적이 아닌 중첩 테스트 클래스임을 나타냄.                                                     |
| @Tag                   | 	클래스 또는 메소드 레벨에서 태그를 선언할 때 사용함.  이를 메이븐을 사용할 경우 설정에서 테스트를 태그를 인식해 포함하거나 제외시킬 수 있음. |
| @Disabled              | 	이 클래스나 테스트를 사용하지 않음을 표시함.	@Ignore                                                 |
| @Timeout               | 	테스트 실행 시간을 선언 후 초과되면 실패하도록 설정함.                                                   |
| @ExtendWith            | 	확장을 선언적으로 등록할 때 사용함.                                                              |
| @RegisterExtension     | 	필드를 통해 프로그래밍 방식으로 확장을 등록할 때 사용함.                                                  |
| @TempDir               | 	필드 주입 또는 매개변수 주입을 통해 임시 디렉토리를 제공하는데 사용함.                                          |


## Junit Platform
Group ID: org.junit.platform

Version: 1.9.2

Artifact IDs:

### junit-platform-commons
Common APIs and support utilities for the JUnit Platform. Any API annotated with @API(status = INTERNAL) is intended solely for usage within the JUnit framework itself. Any usage of internal APIs by external parties is not supported!

### junit-platform-console
Support for discovering and executing tests on the JUnit Platform from the console. See Console Launcher for details.

### junit-platform-console-standalone
An executable JAR with all dependencies included is provided in Maven Central under the junit-platform-console-standalone directory. See Console Launcher for details.

### junit-platform-engine
Public API for test engines. See Registering a TestEngine for details.

### junit-platform-jfr
Provides a LauncherDiscoveryListener and TestExecutionListener for Java Flight Recorder events on the JUnit Platform. See Flight Recorder Support for details.

### junit-platform-launcher
Public API for configuring and launching test plans — typically used by IDEs and build tools. See JUnit Platform Launcher API for details.

### junit-platform-reporting
TestExecutionListener implementations that generate test reports — typically used by IDEs and build tools. See JUnit Platform Reporting for details.

### junit-platform-runner
Runner for executing tests and test suites on the JUnit Platform in a JUnit 4 environment. See Using JUnit 4 to run the JUnit Platform for details.

### junit-platform-suite
JUnit Platform Suite artifact that transitively pulls in dependencies on junit-platform-suite-api and junit-platform-suite-engine for simplified dependency management in build tools such as Gradle and Maven.

### junit-platform-suite-api
Annotations for configuring test suites on the JUnit Platform. Supported by the JUnit Platform Suite Engine and the JUnitPlatform runner.

### junit-platform-suite-commons
Common support utilities for executing test suites on the JUnit Platform.

### junit-platform-suite-engine
Engine that executes test suites on the JUnit Platform; only required at runtime. See JUnit Platform Suite Engine for details.

### junit-platform-testkit
Provides support for executing a test plan for a given TestEngine and then accessing the results via a fluent API to verify the expected results.