package com.yandey.rxjava3_android.rxjava_3_basics._33_behavior_subject.case1

import android.annotation.SuppressLint
import io.reactivex.rxjava3.subjects.BehaviorSubject

@SuppressLint("CheckResult")
fun main() {
    val behaviorSubject = BehaviorSubject.createDefault(0)

    behaviorSubject.onNext(1)
    behaviorSubject.onNext(2)
    behaviorSubject.onNext(3)

    // BehaviorSubject act as Observer 1
    behaviorSubject.subscribe(
        { data -> println("Observer 1 received: $data") },
        { error -> println("Observer 1 error: $error") },
        { println("Observer 1 completed") }
    )

    // BehaviorSubject act as Observable
    behaviorSubject.onNext(4)
    behaviorSubject.onNext(5)
    behaviorSubject.onNext(6)

    // BehaviorSubject act as Observer 2
    behaviorSubject.subscribe(
        { data -> println("Observer 2 received: $data") },
        { error -> println("Observer 2 error: $error") },
        { println("Observer 2 completed") }
    )

    // BehaviorSubject act as Observable, and emitted latest data
    behaviorSubject.onNext(7)
    // By calling this, the BehaviorSubject will terminate to send data
    behaviorSubject.onComplete()
    // just example, if this data emitted after onComplete() it will be ignored
    behaviorSubject.onNext(8)
}