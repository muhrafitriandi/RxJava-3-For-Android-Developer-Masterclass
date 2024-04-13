package com.yandey.rxjava3_android.rxjava_3_basics._26_completable_completableobserver

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Completable

fun saveDataAsynchronously(): Completable {
    return Completable.create { emitter ->
        try {
            println("Performing operation...")
            Thread.sleep(2000)
            println("Operation completed successfully")

            emitter.onComplete()
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }
}

@SuppressLint("CheckResult")
fun main() {
    val completable = saveDataAsynchronously()

    completable.subscribe(
        { println("onComplete: All tasks were successful") },
        { error -> println("onError: An error occurred: $error") }
    )
}