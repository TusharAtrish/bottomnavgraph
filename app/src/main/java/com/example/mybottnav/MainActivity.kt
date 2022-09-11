package com.example.mybottnav

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.mybottnav.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavController()
        checkAuth()
        binding.bottomNavigationView.setOnItemSelectedListener {
            onItemSelected(it)
        }
    }

    private fun initNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun checkAuth() {
        FirebaseAuth.getInstance().addAuthStateListener {
            if (it.currentUser == null) {
                navController.navigate(R.id.signInFragment)
                binding.bottomNavigationView.visibility = View.GONE
            } else {
                navController.navigate(R.id.homeFragment)
                binding.bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }

    private fun onItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.miMessages -> {
                navController.navigate(R.id.messageFragment)
            }
            R.id.miSettings -> {
                navController.navigate(R.id.settingFragment)
            }
            R.id.miProfile -> {
                navController.navigate(R.id.profileFragment)
            }
            else -> {
                navController.navigate(R.id.homeFragment)
            }
        }
        return true
    }

    var doubleBackToExitPressedOnce = false

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            finish()
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            doubleBackToExitPressedOnce = false
        }, 2000)
    }
}