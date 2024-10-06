package creationalPatterns

data class User(
    val name: String,
    val role: Role,
    val permissions: Set<String>,
) {
    fun hasPermission(permission: String) = permission in permissions
}

enum class Role {
    ADMIN,
    SUPER_ADMIN,
    REGULAR_USER,
}

fun main() {
    // 실제 애플리케이션에서는 사용자 데이터베이스에 해당
    val allUsers = mutableListOf<User>()

    fun createUser(name: String, role: Role) {
        for (u in allUsers) {
            if (u.role == role) {
                allUsers += User(name, role, u.permissions)
                return
            }
        }
    }
}

// User 클래스에 새로운 속성이 추가되거나, 특정 속성이 Private 로 바뀔때 createUser 메소드는 컴파일 오류가 발생
/**
 * 프로토타입 디자인 패턴을 사용해야 하는 경우
 * - 객체 생성에 많은 비용이 드는 경우 (예를 들어 객체 생성 시 데이터베이스에서 자룔를 조회해야하는 경우)
 * - 비슷하지만 조금씩 다른 객체를 생성하느라 비슷한 코드를 매번 반복하고 싶지 않은 경우
 */
val allUsers = mutableListOf<User>()
fun createUser(name: String, role: Role) {
    for (u in allUsers) {
        if (u.role == role) {
            allUsers += u.copy(name = name)
            return
        }
    }
}