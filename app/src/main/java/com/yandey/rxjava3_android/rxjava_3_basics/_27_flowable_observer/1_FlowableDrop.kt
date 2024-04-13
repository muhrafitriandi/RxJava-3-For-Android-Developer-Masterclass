package com.yandey.rxjava3_android.rxjava_3_basics._27_flowable_observer

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Flowable
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
fun main() {
    val flowableDrop = Flowable.interval(1, TimeUnit.SECONDS)
        .onBackpressureDrop()
        .doOnNext { item -> println("Sender: $item") }

    flowableDrop.blockingSubscribe(
        { item ->
            Thread.sleep(5000)
            println("onNext (DROP): $item")
        },
        { error -> println("onError (DROP): $error") },
        { println("onComplete (DROP)") },
        3
    )
}