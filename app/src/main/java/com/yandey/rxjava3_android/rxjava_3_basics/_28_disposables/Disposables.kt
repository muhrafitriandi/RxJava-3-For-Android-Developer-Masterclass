package com.yandey.rxjava3_android.rxjava_3_basics._28_disposables

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
fun main() {
    val observable = Observable.interval(1, TimeUnit.SECONDS)

    val disposable: Disposable = observable.subscribe(
        { println("Received: $it") },
        { println("Error: $it") },
        { println("Completed") }
    )

    println("Before dispose: ${disposable.isDisposed}")
    Thread.sleep(5000)
    disposable.dispose()
    println("After dispose: ${disposable.isDisposed}")
}