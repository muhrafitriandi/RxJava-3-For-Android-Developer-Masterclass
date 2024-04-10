package com.yandey.rxjava3_android.rxjava_3_basics._19_merge_operator

import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

fun main() {
    // Observable dengan interval 1s, menghasilkan nilai 1, 2, dan 3
    val observable1 = Observable.intervalRange(1, 3, 0, 1, TimeUnit.SECONDS)

    // Observable dengan interval 2s, menghasilkan nilai 10, 11, dan 12
    val observable2 = Observable.intervalRange(10, 3, 0, 2, TimeUnit.SECONDS)

    Observable.merge(observable1, observable2)
        .blockingSubscribe { println(it) }
}