package io.github.pengdst.githubpage.components.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import io.github.pengdst.githubpage.components.adapters.UserAdapter
import io.github.pengdst.githubpage.components.ui.base.BindingFragment
import io.github.pengdst.githubpage.components.viewmodels.FollowingViewModel
import io.github.pengdst.githubpage.databinding.FragmentFollowersBinding
import io.github.pengdst.githubpage.datas.domain.models.UserDetail
import io.github.pengdst.libs.ui.viewbinding.activity.ActivityViewBindingDelegate.Extension.viewBindings
import javax.inject.Inject

@AndroidEntryPoint
class FollowingsFragment : BindingFragment<FragmentFollowersBinding>() {

    private var username: String? = null

    override val binding: FragmentFollowersBinding by viewBindings()
    private val followingViewModel: FollowingViewModel by viewModels()

    @Inject lateinit var followingsAdapter: UserAdapter

    companion object {

        fun newInstance(usernameArgs: String): FollowingsFragment {
            return FollowingsFragment().apply {
                username = usernameArgs
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        getFollowing()
    }

    private fun setupRecyclerView() {
        with(binding.recyclerviewUser){
            adapter = followingsAdapter
        }
    }

    private fun getFollowing() {
        lifecycleScope.launchWhenStarted {
            try {
                val response = followingViewModel.getFollowing(username.toString())

                if (response.isSuccessful) {
                    response.body()?.let { updateListOfFollowers(it) }
                } else {
                    shortToast(toString())
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    private fun updateListOfFollowers(followings: List<UserDetail>) {
        followingsAdapter.submitList(followings)
    }
}