package com.yandey.rxjava3_android.rxjava_3_basics._13_skip_operator

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
fun main() {
    // Example using skip(count)
    val observable1 = Observable.range(1, 5)
    observable1.skip(2)
        .subscribe { println(it) } // Output: 3, 4, 5

    // Example using skip(time, unit)
    val observable2 = Observable.interval(1, TimeUnit.SECONDS) // will emit data for every one second
    observable2.skip(2, TimeUnit.SECONDS) // will skip data 0 and 1
        .take(5)
        .subscribe { println(it) } // Output: 2, 3, 4, 5, 6
    println("----------")

    // Example using skipLast(count)
    val observable3 = Observable.range(1, 5)
    observable3.skipLast(2)
        .subscribe { println(it) } // Output: 1, 2, 3
    println("----------")

    Thread.sleep(10_000)
}
