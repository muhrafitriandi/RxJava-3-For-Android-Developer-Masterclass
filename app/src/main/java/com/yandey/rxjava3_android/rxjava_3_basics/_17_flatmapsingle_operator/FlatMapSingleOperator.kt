package com.yandey.rxjava3_android.rxjava_3_basics._17_flatmapsingle_operator

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

data class User(val id: Int, val name: String)

fun getUserDetail(id: Int): Single<User> {
    return Single.just(User(id, "User$id"))
}

@SuppressLint("CheckResult")
fun main() {
    val userIds = listOf(1, 2, 3, 4, 5)

    Observable.fromIterable(userIds)
        .flatMapSingle { id -> getUserDetail(id) }
        .subscribe { user -> println("User: ${user.name}") }
}