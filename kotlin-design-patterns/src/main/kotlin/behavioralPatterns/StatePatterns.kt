package behavioralPatterns

/**
 * 상태 패턴은 전략 패턴의 일종이라고 생각할 수 있다.
 * 다만 전략 패턴에서는 외부의 클라이언트가 전략을 교체하는 반면,
 * 상태 패턴에서의 상태는 오로지 입력에 의해 내부적으로 변경된다.
 */
interface WhatCanHappen {
    fun seeHero()
    fun getHit(pointsOfDamage: Int)
    fun calmAgain()
}

//sealed class Mood
//data object Still : Mood()
//data object Aggressive : Mood()
//data object Retreating : Mood()
//data object Dead : Mood()
//
//class Snail : WhatCanHappen {
//    private var mood: Mood = Still
//    private var healthPoints = 10
//
//    override fun seeHero() {
//        mood = when (mood) {
//            is Still -> Aggressive
//            else -> mood
//        }
//    }
//
//    override fun getHit(pointsOfDamage: Int) {
//        healthPoints -= pointsOfDamage
//        mood = when {
//            (healthPoints <= 0) -> Dead
//            mood is Aggressive -> Retreating
//            else -> mood
//        }
//    }
//
//    override fun calmAgain() {
//        TODO("Not yet implemented")
//    }
//}

// [큰 큐모의 상태 패턴]
// 위 방법으로 필요한 로직은 거의 다 구현할 수 있다. 그러나 필요한 로직의 규모가 커지면 다른 접근 방법을 사용하는 경우도 있다
// 그런 경우네는 Snail 객체가 더 간결해진다

class Snail {
    internal var mood: Mood = Still(this)

    private var healthPoints = 10
}

sealed class Mood : WhatCanHappen

// 이제 상태 전환 로직은 상태 객체 안에 있다.
class Still(val snail: Snail) : Mood() {
    override fun seeHero() {
        // snail.mood = Aggressive
    }

    override fun getHit(pointsOfDamage: Int) {
        // 이전 로직과 동일
    }

    override fun calmAgain() {
        // Still 상태로 되돌아감
    }
}