package com.yandey.rxjava3_android.rxjava_3_basics._7_repeat_operator

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable

@SuppressLint("CheckResult")
fun main() {
    val observable = Observable.just("Hello, RxJava3!")

    observable.repeat(3)
        .subscribe { println(it) }
}