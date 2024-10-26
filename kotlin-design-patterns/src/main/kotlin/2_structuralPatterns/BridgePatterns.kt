package `2_structuralPatterns`

/**
 * 브리지 디자인 패턴의 핵심 아이디어는 클래스 계층 구조를 얕게 만듦으로써 시스템에서 구체 클래스의 수를 줄이는 것이다.
 * 뿐만 아니라 부모 클래스를 수정했을 때 자식 클래스에서 발견하기 어려운 버그가 발생하는 현상을 뜻하는 '깨지기 쉬운 기반 클래스 문제' 를 예방하는 데에도 도움이 된다.
 */
interface Trooper {
    fun move(x: Long, y: Long)
    fun attackRebel(x: Long, y: Long)
    // fun shout(): String // shout() 을 추가하는 순간 이 인터페이스를 구현하던 모든 클래스를 모두 변경해야한다.
}

//class StormTrooper: Trooper {
//    override fun move(x: Long, y: Long) {
//        // 보통 속도로 이동
//    }
//
//    override fun attackRebel(x: Long, y: Long) {
//        // 대부분 빗나감
//    }
//}
//
//class ShockTrooper: Trooper {
//    override fun move(x: Long, y: Long) {
//        // 일반적인 StormTrooper 보다는 느리게 이동
//    }
//
//    override fun attackRebel(x: Long, y: Long) {
//        // 명중할 때도 있음
//    }
//}

//////////////////////// 변경 사항에 다리 놓기 //////////////////////////

data class StormTrooper(
    private val weapon: Weapon,
    private val legs: Legs
) : Trooper {
    override fun move(x: Long, y: Long) {
        legs.move(x, y)
    }

    override fun attackRebel(x: Long, y: Long) {
        weapon.attack(x, y)
    }
}

typealias PointsOfDamage = Long
typealias Meters = Int
interface Weapon {
    fun attack(x: Long, y: Long): PointsOfDamage
}
interface Legs {
    fun move(x: Long, y: Long): Meters
}

const val RIFLE_DAMAGE = 3L
const val REGULAR_SPEED: Meters = 1

class Rifle : Weapon {
    override fun attack(x: Long, y: Long): PointsOfDamage = RIFLE_DAMAGE
}
class Flamethrower : Weapon {
    override fun attack(x: Long, y: Long): PointsOfDamage = RIFLE_DAMAGE * 2
}
class Batton : Weapon {
    override fun attack(x: Long, y: Long): PointsOfDamage = RIFLE_DAMAGE * 3
}

class RegularLegs : Legs {
    override fun move(x: Long, y: Long): Meters = REGULAR_SPEED
}
class AthleticLegs : Legs {
    override fun move(x: Long, y: Long): Meters = REGULAR_SPEED * 2
}

val stormTrooper = StormTrooper(Rifle(), RegularLegs())
val flameTrooper = StormTrooper(Flamethrower(), RegularLegs())
val scoutTrooper = StormTrooper(Rifle(), AthleticLegs())

// 현업에서 브리지 패턴은 의존성 주입과 함께 사용될 때가 많다. 예를 들어 브리지 패턴을 사용하면 실제 데이터베이스를 사용하는 구현체를 목 객체로 대체할 수 있다.