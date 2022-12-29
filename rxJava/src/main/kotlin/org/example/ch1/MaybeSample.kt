package org.example.ch1

import io.reactivex.Maybe
import io.reactivex.MaybeObserver
import io.reactivex.disposables.Disposable
import java.time.DayOfWeek
import java.time.LocalDateTime

/**
 * Maybe 는 데이터를 1건만 통지하거나 1건도 통지하지 않고 완료를 통지하거나 에러를 통지하는 클래스
 * 그래서 Maybe 에서는 데이터 통지가 처리 완료를 의미하므로 굳이 다시 완료 통지를 하지 않음
 * Maybe 가 완료 통지를 할 때는 데이터가 1건도 없이 처리가 정상적으로 종료 될 때 입니다.
 */
fun main(args: Array<String>) {
    // Maybe 생성
    val maybe = Maybe.create { emitter ->
        emitter.onSuccess(LocalDateTime.now().dayOfWeek)
    }

    // 구독
    maybe.subscribe(object: MaybeObserver<DayOfWeek> {
        // 구독 준비가 됐을 때의 처리
        override fun onSubscribe(d: Disposable) {
            // do Noting
        }

        // 에러 통지를 받았을 때의 처리
        override fun onError(e: Throwable) {
            println("error : e")
        }

        // 완료 통지를 받을 때의 처리
        override fun onComplete() {
            println("완료")
        }

        // 데이터 통지를 받았을 때의 처리
        override fun onSuccess(value: DayOfWeek) {
            println(value)
        }
    })
}