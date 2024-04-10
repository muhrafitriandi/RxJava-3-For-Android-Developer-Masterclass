package com.yandey.rxjava3_android.rxjava_3_basics._21_startwith_operator

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable

@SuppressLint("CheckResult")
fun main() {
    val observable = Observable.just("Banana", "Apricot", "Avocado")

    observable.startWith(Observable.just("Apple", "Orange"))
        .subscribe { println(it) }
}