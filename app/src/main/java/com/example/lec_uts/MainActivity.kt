package com.example.lec_uts

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.NavController
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.FirebaseApp
class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var fabMain: FloatingActionButton
    private lateinit var fabOption1: FloatingActionButton
    private lateinit var fabOption2: FloatingActionButton
    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var batasNavbar: LinearLayout

    private var isFabMenuOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContentView(R.layout.activity_main)

        // Set up the bottom navigation view
        bottomNavigation = findViewById(R.id.bottom_navigation)
        bottomAppBar = findViewById(R.id.bottomAppBar)
        batasNavbar = findViewById(R.id.batasNavbar)

        // Find the floating action buttons
        fabMain = findViewById(R.id.fabMain)
        fabOption1 = findViewById(R.id.fabOption1)
        fabOption2 = findViewById(R.id.fabOption2)
        fabOption1.setImageResource(R.drawable.food_icon)
        fabOption2.setImageResource(R.drawable.workout_icon)

        // Set up the NavController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController

        // Hide Bottom Navigation for specific fragments (like Onboarding and Food details)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.onBoard1Fragment, R.id.onBoard2Fragment, R.id.onBoard3Fragment, R.id.CreateOrLoginFragment, R.id.LoginFragment,
                R.id.CreateFragment ,R.id.addFoodFragment, R.id.addDetailFoodFragment, R.id.createFoodFragment, R.id.detailCreateFoodFragment -> {
                    bottomNavigation.visibility = View.GONE
                    fabMain.visibility = View.GONE
                    bottomAppBar.visibility = View.GONE
                    batasNavbar.visibility = View.GONE
                }
                else -> {
                    bottomNavigation.visibility = View.VISIBLE
                    fabMain.visibility = View.VISIBLE
                    bottomAppBar.visibility = View.VISIBLE
                    batasNavbar.visibility = View.VISIBLE
                }
            }
        }

        // Handle navigation item selection
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.calendar -> {
                    navController.navigate(R.id.calendarFragment)
                    true
                }
                R.id.water -> {
                    navController.navigate(R.id.waterFragment)
                    true
                }
                R.id.setting -> {
                    navController.navigate(R.id.settingFragment)
                    true
                }
                else -> false
            }
        }

        // Commented out to avoid forcing the app to start on home fragment
        // Set default tab (remove this if you are starting on the onboarding screen)
        // bottomNavigation.selectedItemId = R.id.home

        // Handle FAB Main click for menu animation
        fabMain.setOnClickListener {
            if (isFabMenuOpen) {
                closeFabMenu()
            } else {
                openFabMenu()
            }
        }

        // Navigate to Food Fragment when fabOption1 is clicked and close the FAB menu
        fabOption1.setOnClickListener {
            navController.navigate(R.id.foodFragment)
            closeFabMenu()
            // Uncheck "Home" item
            bottomNavigation.menu.findItem(R.id.home).isChecked = false
        }

        // Navigate to Workout Fragment when fabOption2 is clicked and close the FAB menu
        fabOption2.setOnClickListener {
            // Navigate to WorkoutActivity
            val intent = Intent(this, WorkoutActivity::class.java)
            startActivity(intent)

            // Close the FAB menu if necessary
            closeFabMenu()

            // Uncheck the "Home" item in the bottom navigation
            bottomNavigation.menu.findItem(R.id.home).isChecked = false
        }

    }
    private fun openFabMenu() {
        isFabMenuOpen = true

        fabOption1.visibility = View.VISIBLE
        fabOption2.visibility = View.VISIBLE

        fabOption1.animate()
            .translationY(-400f)
            .alpha(1f)
            .setDuration(300)
            .start()

        fabOption2.animate()
            .translationY(-400f)
            .alpha(1f)
            .setDuration(300)
            .start()
    }

    private fun closeFabMenu() {
        isFabMenuOpen = false

        fabOption1.animate()
            .translationY(0f)
            .alpha(0f)
            .setDuration(300)
            .withEndAction { fabOption1.visibility = View.GONE }
            .start()

        fabOption2.animate()
            .translationY(0f)
            .alpha(0f)
            .setDuration(300)
            .withEndAction { fabOption2.visibility = View.GONE }
            .start()
    }
}
