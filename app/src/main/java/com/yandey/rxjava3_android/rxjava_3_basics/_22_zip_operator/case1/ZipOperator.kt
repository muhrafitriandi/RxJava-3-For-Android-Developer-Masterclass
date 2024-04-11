package com.yandey.rxjava3_android.rxjava_3_basics._22_zip_operator.case1

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable

@SuppressLint("CheckResult")
fun main() {
    val observable1 = Observable.just(1, 2, 3, 4, 5)
    val observable2 = Observable.just("A", "B", "C", "D")

    Observable.zip(observable1, observable2) { number, letter ->
        "$number$letter"
    }.subscribe { println(it) }
}