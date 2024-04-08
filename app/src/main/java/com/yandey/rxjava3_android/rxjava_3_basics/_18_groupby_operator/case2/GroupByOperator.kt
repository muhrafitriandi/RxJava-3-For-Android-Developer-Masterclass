package com.yandey.rxjava3_android.rxjava_3_basics._18_groupby_operator.case2

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable

@SuppressLint("CheckResult")
fun main() {
    val observable = Observable.just("Apple", "Banana", "Apricot", "Avocado", "Blueberry")

    // Manual
    /*
    observable.groupBy { it.first() }
        .flatMap { groupedObservable ->
            println("Key: ${groupedObservable.key}")
            groupedObservable.map { items ->
                "${groupedObservable.key}: $items"
            }
        }
        .subscribe { println(it) }

     */

    // Simple
    observable.groupBy { it.first() }
        .subscribe { groupedObservable ->
            println("Key: ${groupedObservable.key}")
            groupedObservable.subscribe { println("Value: $it") }
        }
}