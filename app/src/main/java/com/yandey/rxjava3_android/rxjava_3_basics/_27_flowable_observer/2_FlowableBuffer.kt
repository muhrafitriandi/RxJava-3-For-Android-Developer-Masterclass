package com.yandey.rxjava3_android.rxjava_3_basics._27_flowable_observer

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Flowable
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
fun main() {
    val flowableBuffer = Flowable.interval(1, TimeUnit.SECONDS)
        .onBackpressureBuffer(1) {
            println("Buffer is full")
        }
        .doOnNext { item -> println("Sender: $item") }

    flowableBuffer.blockingSubscribe(
        { item ->
            Thread.sleep(5000)
            println("onNext (BUFFER): $item")
        },
        { error -> println("onError (BUFFER): $error") },
        { println("onComplete (BUFFER)") },
        1
    )
}