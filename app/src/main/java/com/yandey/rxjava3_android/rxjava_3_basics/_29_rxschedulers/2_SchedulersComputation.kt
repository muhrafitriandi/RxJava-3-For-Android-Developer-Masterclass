package com.yandey.rxjava3_android.rxjava_3_basics._29_rxschedulers

import android.annotation.SuppressLint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

@SuppressLint("CheckResult")
fun schedulersComputation() {
    Observable.fromCallable {
        println("SchedulersComputation - Subscribe on thread: ${Thread.currentThread().name}")
        performComplexCalculation()
    }
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { result ->
            println("SchedulersComputation - Observe on thread: ${Thread.currentThread().name}")
            updateUI(result)
        }
}

fun performComplexCalculation(): Int {
    Thread.sleep(1000)
    return 42
}

fun updateUI(result: Int) {
    println("SchedulersComputation - Updated UI with result: $result")
}
