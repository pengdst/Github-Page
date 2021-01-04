package io.github.pengdst.githubpage.components.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.pengdst.githubpage.R
import io.github.pengdst.githubpage.databinding.ActivityMainBinding
import io.github.pengdst.githubpage.util.extensions.setDrawerLockModeWhen

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

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
                }
                drawerLayout.setDrawerLockModeWhen(destination.id, R.id.homeFragment)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}