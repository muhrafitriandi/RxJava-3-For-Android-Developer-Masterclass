package com.yandey.rxjava3_android.rxjava_3_basics._9_timer_operator

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
fun main() {
    val observable = Observable.timer(3, TimeUnit.SECONDS)

    observable.subscribe { println("Timer expired") }

    Thread.sleep(5000)
}