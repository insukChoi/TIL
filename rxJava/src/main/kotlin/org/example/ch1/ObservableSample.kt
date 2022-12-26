package org.example.ch1

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun main(args: Array<String>) {
    // 인사말을 통지하는 Observable 생성
    val observable: Observable<String> = Observable.create { emitter ->
        // 통지 데이터
        val datas = arrayOf("Hello, World!", "안녕, RxJava!")

        for (data in datas) {
            // 구독이 해지되면 처리를 중단한다.
            if (emitter.isDisposed) return@create

            // 데이터를 통지한다.
            emitter.onNext(data)
        }

        // 완료를 통지한다.
        emitter.onComplete()
    }


    observable
        // Schedulers 처리를 개별 스레드에서 실행한다.
        .observeOn(Schedulers.computation())
        // 구독한다.
        .subscribe(object: Observer<String> {
            override fun onSubscribe(d: Disposable) {
                // 아무것도 하지 않는다
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

            override fun onNext(item: String) {
                val threadName = Thread.currentThread().name
                println("$threadName : $item")
            }

            // 완료 통지 시 처리
            override fun onComplete() {
                // 실행 중인 스레드 이름을 얻는다.
                val threadName = Thread.currentThread().name
                println("$threadName : 완료")
            }
        })

    // 잠시 기다린다.
    Thread.sleep(500L)
}