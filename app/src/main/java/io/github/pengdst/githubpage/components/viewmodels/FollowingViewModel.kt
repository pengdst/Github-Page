package io.github.pengdst.githubpage.components.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import io.github.pengdst.githubpage.datas.repositories.FollowingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FollowingViewModel @ViewModelInject constructor(
    private val repository: FollowingRepository
) : ViewModel() {

    suspend fun getFollowing(username: String) = withContext(Dispatchers.IO) {
        repository.getFollowing(username)
    }

}