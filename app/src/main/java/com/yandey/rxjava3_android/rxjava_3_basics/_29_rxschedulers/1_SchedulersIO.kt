package com.yandey.rxjava3_android.rxjava_3_basics._29_rxschedulers

import android.annotation.SuppressLint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

@SuppressLint("CheckResult")
fun schedulersIO() {
    Observable.fromCallable {
        println("SchedulersIO - Subscribe on thread: ${Thread.currentThread().name}")
        readDataFromDatabase()
    }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { data ->
            println("SchedulersIO - Observe on thread: ${Thread.currentThread().name}")
            updateUI(data)
        }
}

fun readDataFromDatabase(): String {
    Thread.sleep(1000)
    return "Data from database"
}

fun updateUI(data: String) {
    println("SchedulersIO - Updated UI with data: $data")
}
