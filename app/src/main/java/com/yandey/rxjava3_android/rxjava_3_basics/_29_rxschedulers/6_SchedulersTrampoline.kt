package com.yandey.rxjava3_android.rxjava_3_basics._29_rxschedulers

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

@SuppressLint("CheckResult")
fun schedulersTrampoline() {
    Observable.just("Task 1", "Task 2")
        .doOnSubscribe {
            println("SchedulersTrampoline - Subscribe on thread: ${Thread.currentThread().name}")
        }
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.trampoline())
        .subscribe { task ->
            println("SchedulersTrampoline - Observe on thread: ${Thread.currentThread().name}")
            println("SchedulersTrampoline - Running $task on new thread")
        }
}