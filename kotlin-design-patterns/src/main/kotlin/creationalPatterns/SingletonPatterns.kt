package creationalPatterns

/**
 * 싱글톤 패턴
 * - 시스템에 인스턴스가 딱 하나만 존재해야 한다.
 * - 시스템의 모든 부분에서 인스턴스에 접근할 수 있어야 한다.
 *
 * >> 게이른 인스턴스 생성 (Lazy initialization)
 * >> 스레드 안전한 인스턴스 생성
 * >> 고성능의 인스턴스 생성
 */
object NoMoviesList : List<String> { // emptyList() 와 비슷
    override val size: Int = 0

    override fun contains(element: String): Boolean = false

    override fun containsAll(elements: Collection<String>): Boolean {
        TODO("Not yet implemented")
    }

    override fun get(index: Int): String {
        TODO("Not yet implemented")
    }

    override fun indexOf(element: String): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun iterator(): Iterator<String> {
        TODO("Not yet implemented")
    }

    override fun lastIndexOf(element: String): Int {
        TODO("Not yet implemented")
    }

    override fun listIterator(): ListIterator<String> {
        TODO("Not yet implemented")
    }

    override fun listIterator(index: Int): ListIterator<String> {
        TODO("Not yet implemented")
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<String> {
        TODO("Not yet implemented")
    }
}

// Lazy initialization 만족
object Logger {
    init {
        println("싱글톤 객체에 처음 접근했습니다.")
        // 여기에 초기화 로직을 작성
    }
    // 다른 코드는 여기에 작성
}