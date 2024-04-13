package com.yandey.rxjava3_android.rxjava_3_basics._24_single_singleobserver

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Single

@SuppressLint("CheckResult")
fun main() {
    val singleEmpty = Single.just(emptyList<String>())
    val singleValue = Single.just(listOf("A", "B", "C"))

    singleEmpty.subscribe(
        { item -> println("onSuccess: $item") },
        { error -> println("onError: $error") }
    )
    println("==========")
    singleValue.subscribe(
        { item -> println("onSuccess: $item") },
        { error -> println("onError: $error") }
    )
}