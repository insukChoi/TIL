package com.example.kotlincoroutines.ch11

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/*
data class Details(val name: String, val followers: Int)

data class Tweet(val text: String)

fun getFollowersNumber(): Int =
    throw Error("service exception")

suspend fun getUserName(): String {
    delay(500)
    return "marcinmoskala"
}

suspend fun getTweets(): List<Tweet> {
    return listOf(Tweet("Hello, world"))
}

suspend fun CoroutineScope.getUserDetails(): Details {
    val userName = async { getUserName() }
    val followersNumber = async { getFollowersNumber() }
    return Details(userName.await(), followersNumber.await())
}

fun main() = runBlocking {
    val details = try {
        getUserDetails()
    } catch (e: Error) {
        null
    }
    val tweets = async { getTweets() }
    println("User: $details")
    println("Tweets: ${tweets.await()}")
}
 */

// Tweets 는 볼 수 있을 것 같지만, 예외만 발생함
// 이유는 getFollowersNumber 에서 발생한 예외가 async 를 종료시키고, 전체 스코프가 종료되는 걸로 이어져 프로그램이 끝남
