package com.yandey.rxjava3_android.rxjava_3_basics._34_publish_subject.case2

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

@SuppressLint("CheckResult")
fun main() {
    val publishSubject = PublishSubject.create<Int>()

    val sourceObservable: Observable<Int> = Observable.create { emitter ->
        emitter.onNext(1)
        emitter.onNext(2)

        // Returns an error
        emitter.onError(Exception("An error occurred"))

        emitter.onNext(3) // Will be ignored, because the previous data sent caused an error

        // Complete Observable (will not be called because there is already an error)
        emitter.onComplete()
    }

    // PublishSubject act as Observer 1
    publishSubject.subscribe(
        { data -> println("Observer 1 received: $data") },
        { error -> println("Observer 1 error: $error") },
        { println("Observer 1 completed") }
    )

    // Connect PublishSubject to Observable
    sourceObservable.subscribe(publishSubject)

    // PublishSubject act as Observer 2
    publishSubject.subscribe(
        { data -> println("Observer 2 received: $data") },
        { error -> println("Observer 2 error: $error") },
        { println("Observer 2 completed") }
    )
}