package ca.unb.mobiledev.project

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.MediaController
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class Driver_display: AppCompatActivity(){

    lateinit var navControllerDriver: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.driver_homepage)
        val navHostFragmentDriver = supportFragmentManager.findFragmentById(R.id.nav_fragment_driver) as NavHostFragment
        navControllerDriver = navHostFragmentDriver.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav_driver)
        setupWithNavController(bottomNavigationView, navControllerDriver)

    }

}