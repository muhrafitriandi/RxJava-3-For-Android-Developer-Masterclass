package com.yandey.rxjava3_android.rxjava_3_basics._23_observable_observer

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable

@SuppressLint("CheckResult")
fun main() {
    val observable = Observable.just("A", "B", "C")

    observable.subscribe(
        { item -> println("onNext: $item") },
        { error -> println("onError: $error") },
        { println("onComplete") }
    )
}