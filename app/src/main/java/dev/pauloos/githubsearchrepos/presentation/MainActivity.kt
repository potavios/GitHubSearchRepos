package dev.pauloos.githubsearchrepos.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.pauloos.githubsearchrepos.R
import dev.pauloos.githubsearchrepos.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_container) as NavHostFragment

        navController = navHostFragment.navController

        binding.bottomNavMain.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.favoritesFragment,
                R.id.aboutFragment
            )
        )

        binding.appToolbar.setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener {_, destination, _ ->
            val isTopLevelDestination =
                appBarConfiguration.topLevelDestinations.contains(destination.id)

            if (!isTopLevelDestination)
            {
                binding.appToolbar.setNavigationIcon(R.drawable.ic_back)
            }
        }


    }
}