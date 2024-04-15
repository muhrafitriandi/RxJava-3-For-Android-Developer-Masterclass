package com.yandey.rxjava3_android.rxjava_3_basics._37_serialized_subject.solution

import android.annotation.SuppressLint
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.Executors

@SuppressLint("CheckResult")
fun main() {
    val serializedSubject = PublishSubject.create<Int>().toSerialized()
    val executor = Executors.newFixedThreadPool(2)

    executor.execute {
        for (i in 1..5) {
            serializedSubject.onNext(i)
            println("Thread 1 emits: $i")
        }
    }

    executor.execute {
        for (i in 6..10) {
            serializedSubject.onNext(i)
            println("Thread 2 emits: $i")
        }
    }

    serializedSubject.subscribe(
        { item -> println("Observer 1 received: $item") },
        { error -> println("Observer 1 error: $error") },
        { println("Observer 1 completed") }
    )

    serializedSubject.onComplete()
}