package com.yandey.rxjava3_android.rxjava_3_basics._29_rxschedulers

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

@SuppressLint("CheckResult")
fun schedulersNewThread() {
    Observable.just("Task 1", "Task 2")
        .doOnSubscribe {
            println("SchedulersNewThread - Subscribe on thread: ${Thread.currentThread().name}")
        }
        .subscribeOn(Schedulers.newThread())
        .observeOn(Schedulers.newThread())
        .subscribe { task ->
            println("SchedulersNewThread - Observe on thread: ${Thread.currentThread().name}")
            println("SchedulersNewThread - Running $task on new thread")
        }
}