package io.github.pengdst.githubpage.components.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.pengdst.githubpage.R
import io.github.pengdst.githubpage.components.adapters.UserAdapter
import io.github.pengdst.githubpage.components.ui.base.BindingFragment
import io.github.pengdst.githubpage.components.viewmodels.UserViewModel
import io.github.pengdst.githubpage.databinding.FragmentHomeBinding
import io.github.pengdst.githubpage.datas.domain.models.UserDetail
import io.github.pengdst.githubpage.utils.binding.FragmentViewBindingDelegate.Companion.viewBindings
import io.github.pengdst.githubpage.utils.text.addOnLastPositionScrollListener
import io.github.pengdst.githubpage.utils.text.asActionView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>() {

    override val binding: FragmentHomeBinding by viewBindings()
    private val userViewModel: UserViewModel by viewModels()

    private var isLoading = false

    @Inject
    lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        getAllUser()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_top_menu, menu)

        menu.findItem(R.id.search_user)?.let { menuItem ->
            menuItem.asActionView<SearchView>()
                .createSearch(
                    hint = getString(R.string.text_search),
                    onTextChange = {
                        if (it.isEmpty()) getAllUser()
                    },
                    onQuerySubmit = {
                        searchUser(it)
                    }
                )
        }
    }

    private fun getAllUser() {

        lifecycleScope.launchWhenCreated {

            try {
                isLoading = false
                val users = userViewModel.getUsers()
                users?.toList()?.let { updateUI(it) }

            } catch (e: Exception) {
                Timber.e("Error $e")
            }

        }
    }

    private fun searchUser(username: String) {

        lifecycleScope.launchWhenCreated {

            try {
                val response = userViewModel.searchUser(username)

                response?.let {
                    it.item?.let { userList ->
                        updateUI(userList)
                    }
                }

            } catch (e: Exception) {
                Timber.e("Error $e")
            }

        }
    }

    private fun updateUI(users: List<UserDetail>) {
        adapter.submitList(users)
    }

    private fun setupRecyclerView() {
        adapter.setOnItemClickListener { _, userDetail, _ ->

            val action = HomeFragmentDirections.actionHomeFragmentToUserDetailFragment(userDetail)
            findNavController().navigate(action)

        }
        binding.recyclerviewUser.adapter = adapter
        binding.recyclerviewUser.apply {
            addOnLastPositionScrollListener(
                layoutManager = layoutManager as LinearLayoutManager,
                adapter = adapter
            ) {
                if (!isLoading) {
                    loadNewItem()
                }
            }
        }
    }

    private fun loadNewItem() {
        lifecycleScope.launchWhenCreated {
            isLoading = true
            delay(3000L)
            withContext(Dispatchers.Main) {
                getAllUser()
            }
        }
    }

}