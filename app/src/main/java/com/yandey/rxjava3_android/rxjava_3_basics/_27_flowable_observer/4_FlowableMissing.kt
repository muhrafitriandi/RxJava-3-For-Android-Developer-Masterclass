package com.yandey.rxjava3_android.rxjava_3_basics._27_flowable_observer

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
fun main() {
    val flowableMissing = Observable.interval(1, TimeUnit.SECONDS)
        .toFlowable(BackpressureStrategy.MISSING)
        .onBackpressureBuffer(1)
        .doOnNext { item -> println("Sender: $item") }

    flowableMissing.blockingSubscribe(
        { item ->
            Thread.sleep(5000)
            println("onNext (MISSING): $item")
        },
        { error -> println("onError (MISSING): $error") },
        { println("onComplete (MISSING)") },
        1
    )
}