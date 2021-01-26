package io.github.pengdst.githubpage.components.ui.activities

import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.pengdst.githubpage.R
import io.github.pengdst.githubpage.components.ui.base.BaseActivity
import io.github.pengdst.githubpage.databinding.ActivityMainBinding
import io.github.pengdst.githubpage.utils.text.setDrawerLockModeWhen
import io.github.pengdst.libs.ui.extensions.viewBindings

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val binding: ActivityMainBinding by viewBindings()

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onResume() {
        super.onResume()

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment
            ), binding.drawerLayout
        )
        navController = findNavController(R.id.main_nav_host_fragment)
        navController.also {
            with(binding) {
                navigationView.setupWithNavController(it)
                appMain.toolbar.setupWithNavController(it, appBarConfiguration)
                appMain.bottomNavigationView.setupWithNavController(it)
                setSupportActionBar(appMain.toolbar)
            }
        }.addOnDestinationChangedListener { _, destination, _ ->
            with(binding){
                with(appMain) {
                    toolbar.isVisible = when (destination.id) {
                        R.id.homeFragment, R.id.userDetailFragment -> {
                            true
                        }
                        else -> {
                            false
                        }
                    }
                    bottomNavigationView.isVisible = when (destination.id) {
                        R.id.homeFragment -> {
                            true
                        }
                        else -> {
                            false
                        }
                    }
                }
                drawerLayout.setDrawerLockModeWhen(destination.id, R.id.homeFragment)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}