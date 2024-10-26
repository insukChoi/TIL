package `1_creationalPatterns`

/**
 * 정적 팩토리 메서드
 *
 * ex)
 * Long l1 = new Long("1"); // 생성자
 * Long l2 = Long.valueOf("1"); // 정적 팩토리 메서드
 *
 * [장점]
 * - 다양한 생성자에 명시적인 이름을 붙일 수 있다
 * - 캐시를 적용할 수 있다. 실제로 Long.valueOf 도 캐시를 한다.
 * - 하위 클래스 생성
 */
class Server private constructor(port: Long) { // 인스턴스가 정적 팩토리 메서드를 통해서만 생성되기를 원할 때는 객체의 기본생성자를 private 로 선언
    init {
        println("$port 포트에서 서버가 시작됐습니다.")
    }

    companion object {
        fun withPort(port: Long) = Server(port)
    }
}