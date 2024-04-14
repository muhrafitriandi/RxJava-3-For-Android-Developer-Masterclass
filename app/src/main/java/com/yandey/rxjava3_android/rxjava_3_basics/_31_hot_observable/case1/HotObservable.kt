package com.yandey.rxjava3_android.rxjava_3_basics._31_hot_observable.case1

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observables.ConnectableObservable

@SuppressLint("CheckResult")
fun main() {
    val hotObservable1: ConnectableObservable<Int> = Observable.range(1, 5).publish()
    val hotObservable2: ConnectableObservable<Int> = Observable.defer {
        println("ConnectableObservable is called!") // It will be printed until `connect()` is called.
        Observable.range(1, 5)
    }.publish() // Convert from Observable to ConnectableObservable

    hotObservable2.connect()

    hotObservable1.subscribe { data ->
        println("Subscriber 1 received: $data")
    }
    hotObservable1.connect()
    println("First subscription completed.")

    hotObservable1.subscribe { data ->
        println("Subscriber 2 received: $data")
    }
    println("Second subscription completed.")
}