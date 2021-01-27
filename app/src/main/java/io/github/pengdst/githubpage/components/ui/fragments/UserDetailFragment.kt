package io.github.pengdst.githubpage.components.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.pengdst.githubpage.R
import io.github.pengdst.githubpage.components.adapters.pagers.UserPagerAdapter
import io.github.pengdst.githubpage.components.adapters.pagers.UserStatePagerAdapter
import io.github.pengdst.githubpage.components.ui.base.BindingFragment
import io.github.pengdst.githubpage.components.viewmodels.UserViewModel
import io.github.pengdst.githubpage.databinding.FragmentUserDetailBinding
import io.github.pengdst.githubpage.datas.domain.models.UserDetail
import io.github.pengdst.githubpage.datas.utils.Resource
import io.github.pengdst.githubpage.utils.number.asFormattedDecimals
import io.github.pengdst.libs.ui.viewbinding.activity.ActivityViewBindingDelegate.Extension.viewBindings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailFragment : BindingFragment<FragmentUserDetailBinding>() {

    override val binding: FragmentUserDetailBinding by viewBindings()
    private val userViewModel: UserViewModel by viewModels()

    private val args: UserDetailFragmentArgs by navArgs()
    private val userDetail: UserDetail get() = args.user

    @Inject lateinit var userPagerAdapter: UserStatePagerAdapter
    @Inject lateinit var glide: RequestManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTabLayoutAndViewPager()
        updateUi(userDetail)
        getDetailUser()

    }

    private fun setupTabLayoutAndViewPager() {
        userPagerAdapter.apply {
            addFragment(
                Pair(
                    getString(R.string.text_followers),
                    FollowersFragment.newInstance(userDetail.username)
                )
            )
            addFragment(
                Pair(
                    getString(R.string.text_followings),
                    FollowingsFragment.newInstance(userDetail.username)
                )
            )
        }
        binding.viewpaggerFollowers.adapter = userPagerAdapter
        binding.tabLayoutFollowers.setupWithViewPager(binding.viewpaggerFollowers)
    }

    private fun getDetailUser() {

        lifecycleScope.launchWhenCreated {

            try {
                withContext(Dispatchers.Main) {
                    userViewModel.getUserDetail(userDetail.username).observe(viewLifecycleOwner){
                        when(it){
                            is Resource.Success -> updateUi(it.data)
                            is Resource.Loading -> Unit
                            is Resource.Error -> longToast(it.errorMessage)
                        }
                    }
                }

            } catch (e: Exception) {
                Timber.e("error ${e.stackTraceToString()}")
            }

        }
    }

    private fun updateUi(userDetail: UserDetail) {
        with(binding) {
            requireAppCompatActivity().supportActionBar?.title = userDetail.name
            textViewFullname.text = userDetail.name
            textViewUsername.text = userDetail.username

            textViewFollowers.text = userDetail.followers.followers.asFormattedDecimals()
            textViewFollowings.text = userDetail.following.following.asFormattedDecimals()
            textViewRepositories.text = userDetail.repos.publicRepos.asFormattedDecimals()

            glide.load(userDetail.url.avatarUrl)
                .into(imageProfile)
        }
    }
}