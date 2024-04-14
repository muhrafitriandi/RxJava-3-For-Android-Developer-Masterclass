package com.yandey.rxjava3_android.rxjava_3_basics._29_rxschedulers

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Executors

@SuppressLint("CheckResult")
fun schedulersExecutor() {
    val executor = Executors.newFixedThreadPool(2)
    val customScheduler = Schedulers.from(executor)

    Observable.just("Task 1", "Task 2")
        .doOnSubscribe {
            println("SchedulersExecutor - Subscribe on thread: ${Thread.currentThread().name}")
        }
        .subscribeOn(customScheduler)
        .observeOn(customScheduler)
        .subscribe { task ->
            println("SchedulersExecutor - Observe on thread: ${Thread.currentThread().name}")
            println("SchedulersExecutor - Running $task on custom scheduler")
        }

    // executor.shutdown()
}