package com.yandey.rxjava3_android.rxjava_3_basics._25_maybe_maybeobserver

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Maybe

@SuppressLint("CheckResult")
fun main() {
    // val maybeEmpty = Maybe.just(emptyList<String>()) // only onSuccess will be called.
    val maybeEmpty = Maybe.empty<String>() // only onComplete will be called.

    val maybeValue = Maybe.just(listOf("A", "B", "C"))

    maybeEmpty.subscribe(
        { item -> println("onSuccess: $item") },
        { error -> println("onError: $error") },
        { println("onComplete") }
    )
    println("==========")
    maybeValue.subscribe(
        { item -> println("onSuccess: $item") },
        { error -> println("onError: $error") },
        { println("onComplete") }
    )
}