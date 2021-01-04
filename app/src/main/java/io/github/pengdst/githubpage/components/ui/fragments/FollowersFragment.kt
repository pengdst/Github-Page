package io.github.pengdst.githubpage.components.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import io.github.pengdst.githubpage.components.adapters.UserAdapter
import io.github.pengdst.githubpage.components.ui.base.BindingFragment
import io.github.pengdst.githubpage.components.viewmodels.FollowerViewModel
import io.github.pengdst.githubpage.databinding.FragmentFollowersBinding
import io.github.pengdst.githubpage.datas.domain.models.UserDetail
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class FollowersFragment : BindingFragment<FragmentFollowersBinding>() {

    var username: String? = null

    private val followerViewModel: FollowerViewModel by viewModels()
    @Inject lateinit var followersAdapter: UserAdapter

    companion object {

        private const val ARGS_USERNAME = "ARGS_USERNAME"
        fun newInstance(usernameArgs: String): FollowersFragment {
            return FollowersFragment().apply {
                username = usernameArgs
            }
        }
    }

    override fun getViewBinding(inflater: LayoutInflater) = FragmentFollowersBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        getFollowers()
    }

    private fun setupRecyclerView() {
        with(binding.recyclerviewUser){
            adapter = followersAdapter
        }
    }

    private fun getFollowers() {
        lifecycleScope.launchWhenCreated {
            try {
                val response = followerViewModel.getFollowers(username.toString())

                if (response.isSuccessful) {
                    response.body()?.let {
                        updateListOfFollowers(it)
                    }
                } else {
                    shortToast(toString())
                }

            } catch (e: Exception) {
                Timber.e("Error $e")
            }
        }
    }

    private fun updateListOfFollowers(followers: List<UserDetail>) {
        followersAdapter.submitList(followers)
    }
}