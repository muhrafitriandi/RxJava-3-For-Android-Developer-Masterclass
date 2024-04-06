package com.yandey.rxjava3_android.rxjava_3_basics._6_range_operator

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable

@SuppressLint("CheckResult")
fun main() {
    val observable = Observable.range(1, 10)

    observable.subscribe { println(it) }
}