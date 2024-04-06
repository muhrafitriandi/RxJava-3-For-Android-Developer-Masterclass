package com.yandey.rxjava3_android.rxjava_3_basics._3_fromarray_operator

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable

@SuppressLint("CheckResult")
fun main() {
    val array = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)

    // val observable = Observable.fromArray(array1, array2, array3.., array11) // safe, not error
    val observable = Observable.fromArray(array)
    observable.subscribe { println(it.contentToString()) }
}