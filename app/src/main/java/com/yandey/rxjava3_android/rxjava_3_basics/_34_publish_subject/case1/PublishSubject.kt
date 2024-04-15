package com.yandey.rxjava3_android.rxjava_3_basics._34_publish_subject.case1

import android.annotation.SuppressLint
import io.reactivex.rxjava3.subjects.PublishSubject

@SuppressLint("CheckResult")
fun main() {
    val publishSubject = PublishSubject.create<Int>()

    // Data that has been emitted before subscribing will be ignored.
    publishSubject.onNext(1) // ignored
    publishSubject.onNext(2) // ignored
    publishSubject.onNext(3) // ignored

    // PublishSubject act as Observer 1
    publishSubject.subscribe(
        { data -> println("Observer 1 received: $data") },
        { error -> println("Observer 1 error: $error") },
        { println("Observer 1 completed") }
    )

    // PublishSubject act as Observable
    publishSubject.onNext(4)
    publishSubject.onNext(5)
    publishSubject.onNext(6)

    // PublishSubject act as Observer 2
    publishSubject.subscribe(
        { data -> println("Observer 2 received: $data") },
        { error -> println("Observer 2 error: $error") },
        { println("Observer 2 completed") }
    )

    // PublishSubject act as Observable, and emitted latest data
    publishSubject.onNext(7)

    // By calling this, the PublishSubject will terminate to send data
    publishSubject.onComplete()

    // just example, if this data emitted after onComplete() it will be ignored
    publishSubject.onNext(8)
}