package io.github.pengdst.githubpage.components.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import io.github.pengdst.githubpage.components.adapters.UserAdapter
import io.github.pengdst.githubpage.components.ui.base.BindingFragment
import io.github.pengdst.githubpage.components.viewmodels.FollowerViewModel
import io.github.pengdst.githubpage.databinding.FragmentFollowersBinding
import io.github.pengdst.githubpage.datas.domain.models.UserDetail
import io.github.pengdst.libs.ui.viewbinding.activity.ActivityViewBindingDelegate.Extension.viewBindings
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class FollowersFragment : BindingFragment<FragmentFollowersBinding>() {

    private var username: String? = null

    override val binding: FragmentFollowersBinding by viewBindings()
    private val followerViewModel: FollowerViewModel by viewModels()

    @Inject lateinit var followersAdapter: UserAdapter

    companion object {
        fun newInstance(usernameArgs: String): FollowersFragment {
            return FollowersFragment().apply {
                username = usernameArgs
            }
        }
    }

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