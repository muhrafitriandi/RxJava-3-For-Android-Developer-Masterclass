package com.yandey.rxjava3_android.rxjava_3_basics._22_zip_operator.case2.data

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable

@SuppressLint("CheckResult")
fun main() {
    val userList = listOf(
        User(1, "John", 30),
        User(2, "Alice", 25),
        User(3, "Bob", 35)
    )

    val blogList = listOf(
        Blog(
            101,
            1,
            "Introduction to RxJava",
            "RxJava is a library for composing asynchronous and event-based programs."
        ),
        Blog(
            102,
            1,
            "Advanced Kotlin features",
            "Kotlin provides advanced features like coroutines and extension functions."
        ),
        Blog(
            103,
            2,
            "Building reactive applications",
            "Reactive applications respond to changes and events in a scalable way."
        )
    )

    val userObservable = Observable.fromIterable(userList)
    val blogObservable = Observable.fromIterable(blogList)

    Observable.zip(blogObservable, userObservable) { blog, user ->
        BlogDetail(blog.id, blog, user)
    }.filter { blogDetail ->
        blogDetail.blog.userId == blogDetail.user.id
    }.subscribe { println(it) }
}