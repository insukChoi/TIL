package org.example.ch1

import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun main(args: Array<String>) {
    // Completable 생성
    val completable = Completable.create { emitter ->
        // 업무 로직 처리

        // 완료 통지
        emitter.onComplete()
    }

    // 구독
    completable
        .subscribeOn(Schedulers.computation()) // 비동기로 실행
        .subscribe(object : CompletableObserver {
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
        })

    Thread.sleep(100L)
}