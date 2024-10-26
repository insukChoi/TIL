package `3_behavioralPatterns`

/**
 * 전략 패턴의 목표는 객체의 동작을 런타임에 변경하는 것이다.
 */

enum class Direction {
    LEFT, LIGHT
}

data class Projectile( // 모든 탄환은 좌표쌍과 방향을 가진다.
    private var x: Int,
    private var y: Int,
    private var direction: Direction,
)
// 탄환이 한 종류뿐이었다면 아래처럼 정적 팩토리 메서드 패턴을 사용할 수 있다.
class OurHero {
    private var direction: Direction = Direction.LEFT
    private var x: Int = 42
    private var y: Int = 173

    fun shoot(): Projectile {
        return Projectile(x, y, direction)
    }
}

// 하지만 여러 탄환을 구현하길 원한다면..
// 자바 스타일로 해결하는 법은, 변경되는 부분을 추상화하는 인터페이스를 만든다.

interface Weapon {
    fun shoot(x: Int, y: Int, direction: Direction): Projectile
}

class Peashooter: Weapon {
    override fun shoot(x: Int, y: Int, direction: Direction) = Projectile(x, y, direction)
}
class Banana: Weapon {
    override fun shoot(x: Int, y: Int, direction: Direction) = Projectile(x, y, direction)
}

private var currentWeapon: Weapon = Peashooter()
fun shoot(): Projectile = currentWeapon.shoot(1, 2, Direction.LEFT) // 실제 발사 동작은 들고 있는 무기 객체에 위임

// 다른 무기를 장착하는 기능만 구현하면 된다
fun equip(weapon: Weapon) {
    currentWeapon = weapon
}

//////////// 이것이 전략 패턴의 전부다. 전랙 패턴을 사용하면 알고리즘(예제에서는 무기 Weapon)을 교체할 수 있다 /////////

// 코틀린에서는 클래스를 위처럼 많이 작성하지 않고 더 효율적으로 같은 기능을 구현할 수 있다. 코틀린의 함수가 일급 객체라는 사실 덕분이다.
object Weapons {
    fun peashooter(x: Int, y: Int, direction: Direction): Projectile {
        return Projectile(x, y, direction)
    }

    fun banana(x: Int, y: Int, direction: Direction): Projectile {
        return Projectile(x, y, direction)
    }
}

class OurHeroKotlin {
    var currentWeapon = Weapons::peashooter

    val shoot = fun() {
        currentWeapon(1, 2, Direction.LEFT)
    }
}

fun main() {
    val hero = OurHeroKotlin()
    hero.shoot()
    hero.currentWeapon = Weapons::banana
    hero.shoot()
}
