package com.example.fooddeliveryapp.ui.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.data.repository.MenuRepositoryImpl
import com.example.fooddeliveryapp.data.repository.SharedPreferencesRepositoryImpl
import com.example.fooddeliveryapp.data.source.FirebaseFactory
import com.example.fooddeliveryapp.data.source.MenuDataSource
import com.example.fooddeliveryapp.data.source.SharedPreferencesDataSource
import com.example.fooddeliveryapp.data.viewModel.MenuVMFactory
import com.example.fooddeliveryapp.data.viewModel.MenuViewModel
import com.example.fooddeliveryapp.databinding.ActivityMainBinding
import com.example.fooddeliveryapp.utils.LayoutDrawableConverter

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding


    private val sharedViewModel: MenuViewModel by viewModels {
        MenuVMFactory(MenuRepositoryImpl(MenuDataSource(FirebaseFactory.firestore)), SharedPreferencesRepositoryImpl(SharedPreferencesDataSource(this)))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        showFragmentBasedOnState()
        setupBottomBarView()
        setObservers()


    }


    // Update shared preferences to indicate that the application is not running for the first time anymore
    private fun showFragmentBasedOnState()  {


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Inflate the navigation graph manually to make modifications (copy)
        val navGraph = navController.navInflater.inflate(R.navigation.main_graph)

        val isFirstTime = sharedViewModel.isFirstTimeAppLaunch()

        if(isFirstTime) {
            // show intro fragment
            navGraph.setStartDestination(R.id.mainFragment)

        } else {
            // Show menu fragment
            navGraph.setStartDestination(R.id.menuFragment)
            // Destroy main fragment
            navController.popBackStack()
        }

        // Set the new navigation graph with updated start destination to the NavController
        navController.graph = navGraph

        // Update shared preferences to indicate that the application is not running for the first time anymore
        sharedViewModel.appAlreadyLaunched()

        Log.v("my pref", isFirstTime.toString())

    }

    private fun setupBottomBarView() {


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomBarView = binding.bottomNavView


        bottomBarView.setupWithNavController(navController)

        bottomBarView.setOnItemSelectedListener {menuItem->
            // Limpiar el back stack antes de navegar
            navController.popBackStack()
            when (menuItem.itemId) {
                // Navegar a menu fragment
                R.id.menuFragment -> {
                    navController.navigate(R.id.menuFragment)
                    true
                }
                // Navegar al fragmento de cart
                R.id.cartFragment2 -> {
                    navController.navigate(R.id.cartFragment2)
                    true
                }
                // Navegar profile fragment
                R.id.profileFragment -> {
                    navController.navigate(R.id.profileFragment)
                    true
                }

                else -> false
            }

        }


        // Hide or show bottom navigation view
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if(destination.id == R.id.mainFragment) {
                binding.bottomNavView.visibility = View.GONE
            } else {
                binding.bottomNavView.visibility = View.VISIBLE
            }
        }


    }

    private fun refreshCartIcon(quantity: Int) {


        val customDrawable = LayoutDrawableConverter()
        val iconDrawable = customDrawable.convertLayoutToDrawableWithQuantity(this, R.layout.shopping_cart_quantity, quantity)


        val bottomBarView = binding.bottomNavView


        // Obtiene referencia de menu del BottomNavView
        val menu = bottomBarView.menu

        // encuentra el elemento demenu por id y establece icono
        val menuItemCart = menu.findItem(R.id.cartFragment2)
        menuItemCart.setIcon(iconDrawable)

        Log.v("cart quantity", quantity.toString())
    }

    private fun setObservers() {
        sharedViewModel.currentCartSize.observe(this) { quantity ->

            refreshCartIcon(quantity)
        }
    }

}