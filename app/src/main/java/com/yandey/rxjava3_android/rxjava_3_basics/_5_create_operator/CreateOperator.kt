package com.yandey.rxjava3_android.rxjava_3_basics._5_create_operator

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable

@SuppressLint("CheckResult")
fun main() {
    val observable = Observable.create { emitter ->
        for (i in 1..10) {
            if (i == 5) emitter.onComplete()
            else emitter.onNext(i)
        }
    }
    observable.subscribe {
        println(it)
    }
}