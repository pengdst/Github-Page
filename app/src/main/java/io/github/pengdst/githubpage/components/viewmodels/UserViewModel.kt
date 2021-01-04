package io.github.pengdst.githubpage.components.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import io.github.pengdst.githubpage.datas.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserViewModel @ViewModelInject constructor(
    private val repository: UserRepository
) : ViewModel() {

    suspend fun getUserDetail(username: String) = withContext(Dispatchers.IO) {
        repository.getUserDetail(username)
    }

    suspend fun getUsers() = withContext(Dispatchers.IO) {
        repository.getUsers()
    }

    suspend fun searchUser(username: String) = withContext(Dispatchers.IO) {
        repository.searchUser(username)
    }
}