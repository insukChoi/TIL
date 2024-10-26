package `3_behavioralPatterns`

/**
 * 관찰자 패턴
 * - 발행자와 구독자가 있다.
 * - 발행자에게 어떤 일이 발생하면 모든 구독자가 그 사실을 알게 된다.
 * - 구독자는 런타임에 구독 및 구독 취소를 할 수 있다.
 */

// 동물들은 모두가 울음소리를 내는 제각각의 방법을 갖고있다.
class Bat {
    fun screech() {
        println("이 ---")
    }
}
class Turkey {
    fun gobble() {
        println("꾸륵꾸륵")
    }
}
class Dog {
    fun bark() {
        println("멍멍")
    }
    fun howl() {
        println("아우")
    }
}

// 고양이가 동물들의 지휘자로 뽑혔다.
class Cat {
    private val participants = mutableMapOf<() -> Unit, () -> Unit>()
    fun joinChoir(whatToCall: () -> Unit) {
        participants[whatToCall] = whatToCall
    }
    fun leaveChoir(whatToCall: () -> Unit) {
        participants.remove(whatToCall)
    }

    fun conduct(n: Times) {
        for (p in participants.values) {
            for (i in 1..n) {
                p.invoke()
            }
        }
    }
}
typealias Times = Int

fun main() {
    val catTheConductor = Cat()

    val bat = Bat()
    val dog = Dog()
    val turkey = Turkey()

    // 구독
    catTheConductor.joinChoir(bat::screech)
    catTheConductor.joinChoir(dog::bark)
    catTheConductor.joinChoir(dog::howl)
    catTheConductor.joinChoir(turkey::gobble)

    // 구독 취소
    catTheConductor.leaveChoir(bat::screech)

    catTheConductor.conduct(3)
}

// 근데 고양이가 모든 반복문을 수행하기는 너무 벅차 보인다. 그래서 합창단원들에게 역할을 위임하려고 한다.

enum class SoundPitch { HIGH, LOW }
data class Message(val repeat: Times, val pitch: SoundPitch)

class Bat2 {
    fun screech(message: Message) {
        for (i in 1..message.repeat) {
            println("${message.pitch} 이 ----")
        }
    }
}
