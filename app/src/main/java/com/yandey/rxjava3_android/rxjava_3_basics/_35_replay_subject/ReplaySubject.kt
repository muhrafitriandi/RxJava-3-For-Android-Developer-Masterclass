package com.yandey.rxjava3_android.rxjava_3_basics._35_replay_subject

import android.annotation.SuppressLint
import io.reactivex.rxjava3.subjects.ReplaySubject

@SuppressLint("CheckResult")
fun main() {
    // Create a ReplaySubject with buffer size 2
    val replaySubject = ReplaySubject.createWithSize<Int>(2)

    // ReplaySubject act as Observable
    replaySubject.onNext(1)
    replaySubject.onNext(2)
    replaySubject.onNext(3)

    // ReplaySubject act as Observer 2
    replaySubject.subscribe(
        { data -> println("Observer 1 menerima data: $data") },
        { error -> println("Observer 1 menerima kesalahan: $error") },
        { println("Observer 1 selesai menerima data") }
    )

    // Send additional data
    replaySubject.onNext(4)
    replaySubject.onNext(5)
    replaySubject.onNext(6)

    // ReplaySubject act as Observer 2
    replaySubject.subscribe(
        { data -> println("Observer 2 menerima data: $data") },
        { error -> println("Observer 2 menerima kesalahan: $error") },
        { println("Observer 2 selesai menerima data") }
    )

    replaySubject.onComplete()
}
