package com.yandey.rxjava3_android.rxjava_3_basics._16_flatmap_operator

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable

data class User(val name: String, val friends: List<String>)

@SuppressLint("CheckResult")
fun main() {
    val users = listOf(
        User("Alice", listOf("Bob", "Carol")),
        User("Bob", listOf("Alice", "David")),
        User("Carol", listOf("Alice", "David")),
        User("David", listOf("Bob", "Carol"))
    )

    Observable.fromIterable(users)
        .flatMap { user -> Observable.fromIterable(user.friends) }
        .subscribe { println(it) }
}