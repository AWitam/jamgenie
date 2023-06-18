package io.jamgenie.data.home

class UserRepository {

    private val userDataSource = UserDataSource()

    fun getUser(): User {
        return userDataSource.user
    }
}