package com.yandey.rxjava3_android.rxjava_3_basics._32_async_subject.case1

import android.annotation.SuppressLint
import io.reactivex.rxjava3.subjects.AsyncSubject

@SuppressLint("CheckResult")
fun main() {
    val asyncSubject = AsyncSubject.create<Int>()

    // AsyncSubject act as Observer 1
    asyncSubject.subscribe(
        { data -> println("Observer 1 received: $data") },
        { error -> println("Observer 1 error: $error") },
        { println("Observer 1 completed") }
    )

    // AsyncSubject act as Observable
    asyncSubject.onNext(1)
    asyncSubject.onNext(2)
    asyncSubject.onNext(3)

    // AsyncSubject act as Observer 2
    asyncSubject.subscribe(
        { data -> println("Observer 2 received: $data") },
        { error -> println("Observer 2 error: $error") },
        { println("Observer 2 completed") }
    )

    // AsyncSubject act as Observable, and emitted latest data
    asyncSubject.onNext(4)

    // Without calling this, AsyncSubject will not send latest data to Observer
    asyncSubject.onComplete()

    // just example, if this data emitted after onComplete() it will be ignored
    asyncSubject.onNext(5)
}