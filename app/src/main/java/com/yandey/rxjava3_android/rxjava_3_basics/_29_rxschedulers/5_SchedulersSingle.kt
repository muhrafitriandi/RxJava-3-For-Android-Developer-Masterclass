package com.yandey.rxjava3_android.rxjava_3_basics._29_rxschedulers

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

@SuppressLint("CheckResult")
fun schedulersSingle() {
    Observable.just("Task 1", "Task 2")
        .doOnSubscribe {
            println("SchedulersSingle - Subscribe on thread: ${Thread.currentThread().name}")
        }
        .subscribeOn(Schedulers.single())
        .observeOn(Schedulers.single())
        .subscribe { task ->
            println("SchedulersSingle - Observe on thread: ${Thread.currentThread().name}")
            println("SchedulersSingle - Running $task on new thread")
        }
}