package com.yandey.rxjava3_android.rxjava_3_basics

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable

@SuppressLint("CheckResult")
fun main() {
    val observable1 = Observable.just(1, 2, 3, 1, 2, 4, 5, 1)
    observable1.distinct()
        .subscribe { println(it) } // Output: 1, 2, 3, 4, 5

    val observable2 = Observable.just("apple", "banana", "apple", "orange", "banana")
    observable2.distinct { item -> item.length }
        .subscribe { println(it) } // Output: apple, banana
}
