package com.yandey.rxjava3_android.rxjava_3_basics._27_flowable_observer

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Flowable
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
fun main() {
    val flowableLatest = Flowable.interval(1, TimeUnit.SECONDS)
        .onBackpressureLatest()
        .doOnNext { item -> println("Sender: $item") }

    flowableLatest.blockingSubscribe(
        { item ->
            Thread.sleep(5000)
            println("onNext (LATEST): $item")
        },
        { error -> println("onError (LATEST): $error") },
        { println("onComplete (LATEST)") },
        1
    )
}