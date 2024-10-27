package `6_ DesignedForConcurrency`

import kotlinx.coroutines.*
import kotlin.random.Random

/**
 * 장벽 패턴을 사용하면 프로그램을 잠시 멈추고 여러 개의 동시성 작업이 완료되기를 기다릴 수 있다.
 * 일반적으로 여러 곳에서 자료를 가져올 때 장벽 패턴을 사용한다
 */

data class FavoriteCharacter(
    val name: String,
    val catchphrase: String,
    val picture: ByteArray = Random.nextBytes(42)
)

object Michael {
    suspend fun getFavoriteCharacter() = coroutineScope {
        async {
            FavoriteCharacter("터미네이터", "Hasta la vista, baby")
        }
    }
}

object Taylor {
    suspend fun getFavoriteCharacter() = coroutineScope {
        async {
            FavoriteCharacter("돈 비토 코클레오네","그 자에게 거절할 수 없는 제안을 하겠다")
        }
    }
}

object Me {
    suspend fun getFavoriteCharacter() = coroutineScope {
        async {
            FavoriteCharacter("이니고 몬토야","안녕, 난 이니고 몬토야다")
        }
    }
}

suspend fun main() {
    val characters: List<Deferred<FavoriteCharacter>> = listOf(
        Me.getFavoriteCharacter(),
        Taylor.getFavoriteCharacter(),
        Michael.getFavoriteCharacter()
    )
    println(characters.awaitAll())
}