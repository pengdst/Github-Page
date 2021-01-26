package io.github.pengdst.githubpage.components.ui.fragments

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import io.github.pengdst.githubpage.R
import io.github.pengdst.githubpage.components.ui.base.BindingFragment
import io.github.pengdst.githubpage.databinding.FragmentSplashBinding
import io.github.pengdst.githubpage.utils.binding.FragmentViewBindingDelegate.Companion.viewBindings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SplashFragment : BindingFragment<FragmentSplashBinding>() {

    override val binding: FragmentSplashBinding by viewBindings()

    private var isLoading: Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            while (isLoading) {
                withContext(Dispatchers.Main) {
                    binding.textView.text =
                        TimeUnit.MILLISECONDS.toMillis(SystemClock.currentThreadTimeMillis())
                            .toString()
                    Timber.e("onViewCreated() called ${TimeUnit.MILLISECONDS.toMillis(SystemClock.currentThreadTimeMillis())}")
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            delay(3000)
            isLoading = false
            withContext(Dispatchers.Main) {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }
        }
    }

}