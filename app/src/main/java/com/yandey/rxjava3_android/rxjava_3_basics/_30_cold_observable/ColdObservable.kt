package com.yandey.rxjava3_android.rxjava_3_basics._30_cold_observable

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable

@SuppressLint("CheckResult")
fun main() {
    val coldObservable1 = Observable.range(1, 5)
    val coldObservable2 = Observable.defer {
        println("Observable is called!") // It will not be printed until the observer has subscribed
        Observable.range(1, 5)
    }

    coldObservable1.subscribe { data ->
        println("Subscriber 1 received: $data")
    }
    println("First subscription completed.")

    coldObservable1.subscribe { data ->
        println("Subscriber 2 received: $data")
    }
    println("Second subscription completed.")
}
