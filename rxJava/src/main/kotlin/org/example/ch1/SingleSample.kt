package org.example.ch1

import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import java.time.DayOfWeek
import java.time.LocalDateTime

fun main(args: Array<String>) {
    // Single 생성
    val single = Single.create { emitter ->
        emitter.onSuccess(LocalDateTime.now().dayOfWeek)
    }

    // 구독
    single.subscribe(object: SingleObserver<DayOfWeek> {
        // 구독 준비가 됐을 때의 처리
        override fun onSubscribe(d: Disposable) {
            // do Noting
        }

        // 에러 통지를 받았을 때의 처리
        override fun onError(e: Throwable) {
            println("error : e")
        }

        // 데이터 통지를 받았을 때의 처리
        override fun onSuccess(value: DayOfWeek) {
            println(value)
        }
    })
}