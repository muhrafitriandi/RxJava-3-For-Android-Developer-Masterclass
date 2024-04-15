package com.yandey.rxjava3_android.rxjava_3_basics._36_unicast_subject

import android.annotation.SuppressLint
import io.reactivex.rxjava3.subjects.UnicastSubject

@SuppressLint("CheckResult")
fun main() {
    val unicastSubject = UnicastSubject.create<Int>()

    // UnicastSubject act as Observer 1
    unicastSubject.subscribe(
        { item -> println("Observer 1 received: $item") },
        { error -> println("Observer 1 error: $error") },
        { println("Observer 1 completed") }
    )

    // UnicastSubject act as Observable
    unicastSubject.onNext(1)
    unicastSubject.onNext(2)

    // Try a second observer subscription, this will produce an error
    try {
        unicastSubject.subscribe(
            { item -> println("Observer 2 received: $item") },
            { error -> println("Observer 2 error: $error") },
            { println("Observer 2 completed") }
        )
    } catch (e: Exception) {
        println("Observer 2 failed to subscribe: ${e.message}")
    }

    // Send additional data
    unicastSubject.onNext(3)

    unicastSubject.onComplete()
}
