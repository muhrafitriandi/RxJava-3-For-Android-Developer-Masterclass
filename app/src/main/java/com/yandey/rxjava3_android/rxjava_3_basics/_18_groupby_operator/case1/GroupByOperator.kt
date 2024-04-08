package com.yandey.rxjava3_android.rxjava_3_basics._18_groupby_operator.case1

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable

@SuppressLint("CheckResult")
fun main() {
    val observable = Observable.just("apple", "banana", "apricot", "avocado", "orange")

    observable.groupBy { it[0] }
        .flatMapSingle { groupedObservable ->
            groupedObservable.toList()
                .map { items ->
                    "${groupedObservable.key}: $items"
                }
        }
        .subscribe { println(it) }
}