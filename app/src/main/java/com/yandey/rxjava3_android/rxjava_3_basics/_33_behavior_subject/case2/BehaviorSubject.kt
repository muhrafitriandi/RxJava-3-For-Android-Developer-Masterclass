package com.yandey.rxjava3_android.rxjava_3_basics._33_behavior_subject.case2

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

@SuppressLint("CheckResult")
fun main() {
    val behaviorSubject = BehaviorSubject.createDefault(0)

    val sourceObservable: Observable<Int> = Observable.create { emitter ->
        emitter.onNext(1)
        emitter.onNext(2)

        // Returns an error
        emitter.onError(Exception("An error occurred"))

        emitter.onNext(3) // Will be ignored, because the previous data sent caused an error

        // Complete Observable (will not be called because there is already an error)
        emitter.onComplete()
    }

    // BehaviorSubject act as Observer 1
    behaviorSubject.subscribe(
        { data -> println("Observer 1 received: $data") },
        { error -> println("Observer 1 error: $error") },
        { println("Observer 1 completed") }
    )

    // Connect BehaviorSubject to Observable
    sourceObservable.subscribe(behaviorSubject)

    // BehaviorSubject act as Observer 2
    behaviorSubject.subscribe(
        { data -> println("Observer 2 received: $data") },
        { error -> println("Observer 2 error: $error") },
        { println("Observer 2 completed") }
    )
}