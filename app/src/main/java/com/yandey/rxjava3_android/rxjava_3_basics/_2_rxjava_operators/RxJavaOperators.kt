package com.yandey.rxjava3_android.rxjava_3_basics._2_rxjava_operators

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable

@SuppressLint("CheckResult")
fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
    // val observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11) // error
    // val observable = Observable.just(list1, list2, list3.., list11) // error
    val observable = Observable.just(list)

    observable.subscribe { println(it) }
}