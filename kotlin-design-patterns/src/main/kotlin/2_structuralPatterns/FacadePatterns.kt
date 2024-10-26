package `2_structuralPatterns`

import `1_creationalPatterns`.JsonParser
import `1_creationalPatterns`.Server
import `1_creationalPatterns`.YamlParser
import kotlin.io.path.Path

/**
 * 퍼사드는 지저분한 구현 세부 사항을 감추는 데에 사용한다.
 *
 * 추상 팩토리 패턴은 연관된 클래스를 생성 하는 데 초점을 두지만,
 * 퍼사드 패턴은 일단 생성된 객체들을 잘 사용하는 데 초점을 둔다.
 */

fun Server.startFromConfiguration(fileLocation: String) {
    val path = Path(fileLocation)
    val lines = path.toFile().readLines()
    val configuration = try {
        JsonParser().server(lines)
    } catch (e: RuntimeException) {
        YamlParser().server(lines)
    }

    Server.withPort(configuration.properties[0].value as Long)
}