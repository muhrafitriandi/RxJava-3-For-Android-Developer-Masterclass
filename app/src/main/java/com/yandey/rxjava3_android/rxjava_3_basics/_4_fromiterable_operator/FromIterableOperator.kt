package com.yandey.rxjava3_android.rxjava_3_basics._4_fromiterable_operator

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable

@SuppressLint("CheckResult")
fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)

    // val observable = Observable.fromIterable(list1, list2) // error
    val observable = Observable.fromIterable(list)
    observable.subscribe { println(it) }
}