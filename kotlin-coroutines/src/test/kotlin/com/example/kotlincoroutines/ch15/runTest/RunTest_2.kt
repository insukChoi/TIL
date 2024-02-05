package com.example.kotlincoroutines.ch15.runTest

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * runTest 는 TestScope 를, TestScope 는 StandardTestDispatcher 를,
 * StandardTestDispatcher 는 TestCoroutineScheduler 를 포함합니다.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class FetchUserDataTest {
    @Test
    fun `should load data concurrently`() = runTest {
        // given
        val userRepo = FakeUserDateRepository()
        val userCase = FetchUserUseCase(userRepo)

        // when
        userCase.fetchUserData()

        // then
        assertEquals(1000, currentTime)
    }

    @Test
    fun `should construct user`() = runTest {
        // given
        val userRepo = FakeUserDateRepository()
        val userCase = FetchUserUseCase(userRepo)

        // when
        val result = userCase.fetchUserData()

        // then
        val expectedUser = User(
            name = "Ben",
            friends = listOf(Friend("some-friend-id-1")),
            profile = Profile("Example description")
        )
        assertEquals(expectedUser, result)
    }
}


data class Friend(val id: String)
data class Profile(val description: String)
interface UserDataRepository {
    suspend fun getName(): String
    suspend fun getFriends(): List<Friend>
    suspend fun getProfile(): Profile
}
data class User(
    val name: String,
    val friends: List<Friend>,
    val profile: Profile
)
class FakeUserDateRepository : UserDataRepository {
    override suspend fun getName(): String {
        delay(1000)
        return "Ben"
    }

    override suspend fun getFriends(): List<Friend> {
        delay(1000)
        return listOf(Friend("some-friend-id-1"))
    }

    override suspend fun getProfile(): Profile {
        delay(1000)
        return Profile("Example description")
    }
}
class FetchUserUseCase(
    private val repo: UserDataRepository
) {
    suspend fun fetchUserData(): User = coroutineScope {
        val name = async { repo.getName() }
        val friends = async { repo.getFriends() }
        val profile = async { repo.getProfile() }
        User(
            name = name.await(),
            friends = friends.await(),
            profile = profile.await()
        )
    }
}