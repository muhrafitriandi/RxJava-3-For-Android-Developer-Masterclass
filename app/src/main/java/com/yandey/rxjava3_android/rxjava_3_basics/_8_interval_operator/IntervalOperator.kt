package com.yandey.rxjava3_android.rxjava_3_basics._8_interval_operator

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
fun main() {
    val observable = Observable.interval(1, TimeUnit.SECONDS).takeWhile { it <= 5 }

    observable.subscribe { println("Tick$it") }

    Thread.sleep(10_000)
}