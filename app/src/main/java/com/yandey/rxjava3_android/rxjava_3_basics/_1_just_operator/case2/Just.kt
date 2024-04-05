package com.yandey.rxjava3_android.rxjava_3_basics._1_just_operator.case2

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

@SuppressLint("CheckResult")
fun main() {
    val observable = Observable.just("Hello RxJava3!", "Male")

    val observer = object : Observer<String> {
        override fun onSubscribe(d: Disposable) {
            println("onSubscribe is called!")
        }

        override fun onError(e: Throwable) {
            println("onError is called!")
        }

        override fun onComplete() {
            println("onComplete! is called!")
        }

        override fun onNext(t: String) {
            println("onNext is called!: $t")
        }
    }
    observable.subscribe(observer)
}