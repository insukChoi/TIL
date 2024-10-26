package `2_structuralPatterns`

interface StarTrekRepository {
    fun getCaptain(starshipName: String): String
    fun addCaptain(starshipName: String, captainName: String)
}

class DefaultStarTrekRepository : StarTrekRepository {
    private val startTrekCaptains = mutableMapOf("USS 엔터프라이즈" to "장뤽 피카드")
    override fun getCaptain(starshipName: String): String {
        return startTrekCaptains[starshipName] ?: "알 수 없음"
    }

    override fun addCaptain(starshipName: String, captainName: String) {
        startTrekCaptains[starshipName] = captainName
    }
}

/*
 * - 데코레이션(새로운 동작)을 추가할 대상 객체를 입력으로 받는다.
 * - 대상 객체에 대한 참조를 계속 유지한다.
 * - 데코레이터 클래스의 메서드가 호출되면 들고 있는 대상 객체의 동작을 변경할지 또는 처리를 위임할지 결정한다.
 * - 대상 객체에서 인터페이스를 추출하거나 또는 해당 클래스가 이미 구현하고 있는 인터페이스를 사용한다.
 */
class LoggingGetCaptain(private val repository: StarTrekRepository) : StarTrekRepository by repository {
    override fun getCaptain(starshipName: String): String {
        println("$starshipName 함선의 선장을 조회 중입니다.")
        return repository.getCaptain(starshipName)
    }
}

class ValidatingGetCaptain(private val repository: StarTrekRepository) : StarTrekRepository by repository {
    private val maxNameLength = 7
    override fun addCaptain(starshipName: String, captainName: String) {
        require(captainName.lastIndex < maxNameLength) {
            "${captainName}의 이름이 7자를 넘습니다!"
        }
        repository.addCaptain(starshipName, captainName)
    }
}

fun main() {
    val starTrekRepository = DefaultStarTrekRepository()
    val withValidating = ValidatingGetCaptain(starTrekRepository)
    val withLoggingAndValidating = LoggingGetCaptain(withValidating)
    withLoggingAndValidating.getCaptain("USS 엔터프라이즈")
    withLoggingAndValidating.addCaptain("USS 보이저", "캐서린 제인웨이")
}