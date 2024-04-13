package com.yandey.rxjava3_android.rxjava_3_basics._28_disposables

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
fun main() {
    val compositeDisposable = CompositeDisposable()

    val observable1 = Observable.interval(1, TimeUnit.SECONDS)
    val observable2 = Observable.interval(1, TimeUnit.SECONDS)

    val disposable1: Disposable = observable1.subscribe(
        { println("Received from Observable 1: $it") },
        { println("Error from Observable 1: $it") },
        { println("Observable 1 completed") }
    )

    val disposable2: Disposable = observable2.subscribe(
        { println("Received from Observable 2: $it") },
        { println("Error from Observable 2: $it") },
        { println("Observable 2 completed") }
    )
    compositeDisposable.addAll(disposable1, disposable2)

    println("Before dispose (CompositeDisposable): ${compositeDisposable.isDisposed}")
    println("Before dispose (Disposable 1): ${disposable1.isDisposed}")
    println("Before dispose (Disposable 2): ${disposable2.isDisposed}")
    println("==========")

    Thread.sleep(5000)
    // compositeDisposable.dispose()
    compositeDisposable.clear()

    println("==========")
    println("After dispose (CompositeDisposable): ${compositeDisposable.isDisposed}")
    println("After dispose (Disposable 1): ${disposable1.isDisposed}")
    println("After dispose (Disposable 2): ${disposable2.isDisposed}")
}