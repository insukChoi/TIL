package `1_creationalPatterns`

/**
 * 추상 팩토리란 팩토리를 만들어 내는 팩토리.
 * 즉, 추상 팩토리란 여러 팩토리 메서드를 감싸는 클래스다.
 */
interface Property {
    val name: String
    val value: Any
}

interface ServerConfiguration {
    val properties: List<Property>
}

data class PropertyImpl(override val name: String, override val value: Any) : Property

data class ServerConfigurationImpl(override val properties: List<Property>) : ServerConfiguration

//fun property(prop: String): Property {
//    val (name, value) = prop.split(":")
//    return when (name) {
//        "port" -> PropertyImpl(name, value.trim().toInt())
//        "environment" -> PropertyImpl(name, value.trim())
//        else -> throw RuntimeException("알 수 없는 속성 : $name")
//    }
//}

//fun main() {
//    val portProperty = property("port: 8080")
//    val environmentProperty = property("environment: production")
//
//    val port: Int = portProperty.value // Type mismatch : inferred type is Any but Int was expected
//
//    // 이미 팩토리 메서드에서 port 가 Int 타입으로 파싱됐고 이 정보는 사라졌다. value 는 Any 타입으로 선언되어 있다.
//}

data class IntProperty(override val name: String, override val value: Int) : Property
data class StringProperty(override val name: String, override val value: String) : Property

fun property(prop: String): Property {
    val (name, value) = prop.split(":")
    return when (name) {
        "port" -> IntProperty(name, value.trim().toInt())
        "environment" -> StringProperty(name, value.trim())
        else -> throw RuntimeException("알 수 없는 속성 : $name")
    }
}
/* 여전히 value 는 Any 타입으로 선언되어 있어서 위 main() 예제와 동일한 이슈가 발생됨.
 * 이 경우 스마트캐스팅 (is, as?) 를 사용할 수 있음
 *  - if (portProperty is IntProperty) {..}
 *  - val port: Int? = portProperty.value as? Int
 */


//////////////////// 팩토리 메서드의 모음 ///////////////////
fun server(propertyStrings: List<String>): ServerConfiguration {
    val parsedProperties = mutableListOf<Property>()
    for (p in propertyStrings) {
        parsedProperties += property(p)
    }
    return ServerConfigurationImpl(parsedProperties)
}

//fun main() {
//    println(server(listOf("port: 9090", "environment: production")))
//    // ServerConfigurationImpl(properties=[IntProperty(name=port, value=9090), StringProperty(name=environment, value=production)])
//}

// 클래스로 추상팩토리 구현
class Parser {
    companion object {
        fun property(prop: String): Property {
            TODO()
        }
        fun server(propertyStrings: List<String>): ServerConfiguration {
            TODO()
        }
    }
}

// 인터페이스로 추상팩토리 구현
interface ParserInterface {
    fun property(prop: String): Property
    fun server(propertyStrings: List<String>): ServerConfiguration
}
class YamlParser : ParserInterface {
    override fun property(prop: String): Property {
        TODO("Not yet implemented")
    }
    override fun server(propertyStrings: List<String>): ServerConfiguration {
        TODO("Not yet implemented")
    }
}
class JsonParser : ParserInterface {
    override fun property(prop: String): Property {
        TODO("Not yet implemented")
    }
    override fun server(propertyStrings: List<String>): ServerConfiguration {
        TODO("Not yet implemented")
    }
}