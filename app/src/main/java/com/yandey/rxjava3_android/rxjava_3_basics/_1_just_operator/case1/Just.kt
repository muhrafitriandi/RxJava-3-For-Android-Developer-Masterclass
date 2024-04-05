package com.yandey.rxjava3_android.rxjava_3_basics._1_just_operator.case1

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable

@SuppressLint("CheckResult")
fun main() {
    val observable = Observable.defer {
        println("Observable is called!")
        Observable.just(200) // not used
        Observable.just(100, "Hello RxJava3!")
    }

    observable.subscribe { item -> println(item) }
}