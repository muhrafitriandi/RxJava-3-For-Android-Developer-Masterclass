package com.yandey.rxjava3_android.rxjava_3_basics._31_hot_observable.case2

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observables.ConnectableObservable
import java.util.concurrent.TimeUnit

fun main() {
    val connectableObservable: ConnectableObservable<Long> = Observable
        .interval(1, TimeUnit.SECONDS)
        .publish()

    connectableObservable.connect()

    val subscription1 = connectableObservable.subscribe { data ->
        println("Subscriber 1 received: $data")
    }

    Thread.sleep(5000)

    val subscription2 = connectableObservable.subscribe { data ->
        println("Subscriber 2 received: $data")
    }

    Thread.sleep(1000)

    subscription1.dispose()
    subscription2.dispose()
}
