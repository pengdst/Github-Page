package io.github.pengdst.githubpage.components.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.pengdst.githubpage.components.viewmodels.FollowingViewModel
import io.github.pengdst.githubpage.components.viewmodels.UserViewModel
import io.github.pengdst.githubpage.datas.repositories.BaseRepository
import io.github.pengdst.githubpage.datas.repositories.FollowingRepository
import io.github.pengdst.githubpage.datas.repositories.UserRepository
import java.lang.ClassCastException

class ViewModelFactory(private val repository: BaseRepository): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(UserViewModel::class.java) -> {
                UserViewModel(repository as UserRepository) as T
            }
            else -> if (modelClass.isAssignableFrom(FollowingViewModel::class.java)){
                FollowingViewModel(repository as FollowingRepository)
            } else {
                throw ClassCastException("Failed inject repository ${repository.javaClass.simpleName} to ${modelClass.simpleName}")
            } as T
        }
    }

}