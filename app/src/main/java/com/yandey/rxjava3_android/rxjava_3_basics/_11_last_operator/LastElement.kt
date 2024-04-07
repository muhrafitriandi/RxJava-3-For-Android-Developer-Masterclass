package com.yandey.rxjava3_android.rxjava_3_basics._11_last_operator

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable

@SuppressLint("CheckResult")
fun main() {
    val observable1 = Observable.range(1, 10)
    val observable2 = Observable.fromIterable(emptyList())

    observable1.lastElement()
        .subscribe(
            { value -> println("Last value: $value") },
            { error -> println("Error: $error") },
            {
                
            }
        )

    observable2.lastElement()
        .subscribe(
            { value -> println("Last value: $value") },
            { error -> println("Error: $error") }
        )
}