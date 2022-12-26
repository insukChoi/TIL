package org.example.ch1

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableOnSubscribe
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

fun main(args: Array<String>) {
    val flowable: Flowable<String> = Flowable.create(
        FlowableOnSubscribe { emitter ->
            val datas = arrayOf("Hello, World!", "안녕, RxJava!", "안녕, RxJava1", "안녕, RxJava2", "안녕, RxJava3")
            for (data in datas) {
                // 구독이 해지되면 처리를 중단한다.
                if (emitter.isCancelled) return@FlowableOnSubscribe

                // 데이터를 통지한다.
                emitter.onNext(data)
            }

            // 완료를 통지한다.
            emitter.onComplete()
        }, BackpressureStrategy.BUFFER // // 초과한 데이터는 버퍼링한다.
    )


    flowable
        // Schedulers 처리를 개별 스레드에서 실행한다.
        .observeOn(Schedulers.computation())
        // 구독한다.
        .subscribe(object: Subscriber<String> {

            lateinit var subscription: Subscription

            // 구독 시작시 처리
            override fun onSubscribe(subscription: Subscription?) {
                if (subscription != null) {
                    this.subscription = subscription
                    // 받을 데이터 개수를 요청한다.
                    this.subscription.request(1L)
                }
            }

            // 데이터 받을 때 처리
            override fun onNext(data: String?) {
                // 실행 중인 스레드 이름을 얻는다.
                val threadName = Thread.currentThread().name
                // 받은 데이터를 출력한다.
                println("$threadName : $data")
                // 다음에 받을 데이터 개수를 요청한다.
                this.subscription.request(1L)
            }

            // 완료 통지 시 처리
            override fun onComplete() {
                // 실행 중인 스레드 이름을 얻는다.
                val threadName = Thread.currentThread().name
                println("$threadName : 완료")
            }

            // 에러 통지 시 처리
            override fun onError(error: Throwable?) {
                error?.printStackTrace()
            }
        })

    // 잠시 기다린다.
    Thread.sleep(500L)
}