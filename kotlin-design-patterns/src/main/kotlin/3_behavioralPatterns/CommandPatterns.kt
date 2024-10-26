package `3_behavioralPatterns`

/**
 * 명령 디자인 패턴을 사용하면 객체 내부에 동작을 캡슐화해서 넣어 둔 뒤 나중에 실행되도록 할 수 잇다.
 */
//class Stormtrooper(...) {
//    fun attack(x: Long, y: Long) {
//        println("($x,$y) 공격 중")
//    }
//    fun move(x: Long, y: Long) {
//        println("($x,$y)로 이동 중")
//    }
//}
// 트루퍼가 한 번에 기억할 수 있는 명령이 하나밖에 되지 않는다는 문제를 해결해야한다.
// 어떤 트루퍼가 (0,0)에 있다고 하자. 만약 (20,0)으로 이동하라는 move(20,0) 명령과 (20,20)으로 이동하나는 move(20,20) 명령을 연달아 내리면 이 객체는 (0,0)에서 (20,20)으로 직선으로 이동할 것이다.
// 명령을 하나밖에 기억하지 못하는 것이 문제라면 이를 해결하기 위해서는 무언가의 리스트를 들고 있어야 한다는 것을 알 수 있다.

//class Trooper {
//    private val orders = mutableListOf<Any>()
//
//    fun addOrder(order: Any) {
//        this.orders.add(order)
//    }
//
//    fun executeOrders() {
//        while (orders.isNotEmpty()) {
//            val order = orders.removeFirst()
//            order.execute() // 현재 컴파일 오류 발생
//        }
//    }
//}

// 명령 디자인 패턴이 낯설더라도 이 코드가 동작하도록 하려면 execute()라는 함수하나를 갖는 인터페이스를 구현하면 된다는 정도는 짐작할 수 있다.
//interface Command {
//    fun execute()
//}
//
//class Trooper {
//    private val orders = mutableListOf<Command>()
//
//    fun addOrder(order: Command) {
//        this.orders.add(order)
//    }
//
//    fun executeOrders() {
//        while (orders.isNotEmpty()) {
//            val order = orders.removeFirst()
//            order.execute()
//        }
//    }
//}

// 위 내용이 일반적으로 자바에서 명령 패턴을 구현하는 방식이다.
// 코틀린에서는 더 좋은 방법이 없을까?
typealias KCommand = () -> Unit

//class Trooper {
//    private val orders = mutableListOf<KCommand>()
//
//    fun addOrder(order: KCommand) {
//        this.orders.add(order)
//    }
//
//    fun executeOrders() {
//        while (orders.isNotEmpty()) {
//            val order = orders.removeFirst()
//            order.invoke()
//        }
//    }
//}

// Command 는 매개변수를 받을 수 없다. 함수에 인수가 있으면 어떻게 할까?
// 함수 생성기를 사용하는 방법이 있다.
interface TrooperInterface {
    fun move(x: Int, y: Int)
}
val moveGenerator = fun(trooper: TrooperInterface,
                        x: Int,
                        y: Int): KCommand {
    return fun() {
        trooper.move(x, y)
    }
}

class Trooper : TrooperInterface {
    private val orders = mutableListOf<KCommand>()

    fun appendMove(x: Int, y: Int) = apply {
        orders.add(moveGenerator(this, x, y))
    }

    fun executeOrders() {
        while (orders.isNotEmpty()) {
            val order = orders.removeFirst()
            order.invoke()
        }
    }

    override fun move(x: Int, y: Int) {
        println("($x,$y)로 이동 중")
    }
}

fun main(args: Array<String>) {
    val trooper = Trooper()
    trooper.appendMove(20, 0)
    trooper.appendMove(20, 20)
    trooper.appendMove(5, 20)
        .executeOrders()
}

/**
 * [출력 결과]
 * (20,0)로 이동 중
 * (20,20)로 이동 중
 * (5,20)로 이동 중
 */