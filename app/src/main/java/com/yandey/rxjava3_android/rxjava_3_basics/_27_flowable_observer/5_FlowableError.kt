package com.yandey.rxjava3_android.rxjava_3_basics._27_flowable_observer

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
fun main() {
    val flowableError = Observable.interval(1, TimeUnit.SECONDS)
        .toFlowable(BackpressureStrategy.ERROR)
        .doOnNext { item -> println("Sender: $item") }

    flowableError.blockingSubscribe(
        { item ->
            Thread.sleep(5000)
            println("onNext (ERROR): $item")
        },
        { error -> println("onError (ERROR): $error") },
        { println("onComplete (ERROR)") },
        1
    )
}