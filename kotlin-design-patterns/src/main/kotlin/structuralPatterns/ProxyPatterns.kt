package structuralPatterns

import java.net.URL

data class CatImage(val thumbnailUrl: String, val url: String) {
    val image: ByteArray by lazy {
        URL(url).readBytes()
    }
}

/**
 * lazy 는 위임 함수 중 하나다
 * image 속성을 처음 호출하면 코드 블록이 실행되고 그 결과가 image 속성에 저장 된다. 이후의 호출은 그냥 속성에 저장된 값을 반환한다.
 *
 * 프록시 패턴
 * - 가상 프록시 : 게으른 방식으로 결과를 캐시한다.
 * - 원격 프록시 : 원격지의 자원에 접근한다.
 * - 보호 프록시 또는 접근 제어 프록시 : 인가되지 않은 접근을 거부한다.
 *
 * 앞의 예제 코드는 가상 프록시 또는 가상 프록시와 원격 프록시의 조합이라고 할 수 있다.
 */