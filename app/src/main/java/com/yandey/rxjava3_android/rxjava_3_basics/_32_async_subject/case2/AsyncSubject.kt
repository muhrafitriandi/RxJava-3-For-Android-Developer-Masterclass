package com.yandey.rxjava3_android.rxjava_3_basics._32_async_subject.case2

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.AsyncSubject

@SuppressLint("CheckResult")
fun main() {
    val asyncSubject = AsyncSubject.create<Int>()

    val sourceObservable: Observable<Int> = Observable.create { emitter ->
        emitter.onNext(1)
        emitter.onNext(2)
        emitter.onNext(3)

        // Returns an error
        emitter.onError(Exception("An error occurred"))

        // Complete Observable (will not be called because there is already an error)
        emitter.onComplete()
    }

    // AsyncSubject act as Observer
    asyncSubject.subscribe(
        { data -> println("Observer received: $data") },
        { error -> println("Observer error: $error") },
        { println("Observer completed") }
    )

    // Connect AsyncSubject to Observable
    sourceObservable.subscribe(asyncSubject)
}
