package io.github.pengdst.githubpage.components.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import io.github.pengdst.githubpage.datas.repositories.FollowerRepository
import io.github.pengdst.githubpage.datas.repositories.FollowingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FollowerViewModel @ViewModelInject constructor(
    private val repository: FollowerRepository
) : ViewModel() {

    suspend fun getFollowers(username: String) = withContext(Dispatchers.IO) {
        repository.getFollowers(username)
    }
}