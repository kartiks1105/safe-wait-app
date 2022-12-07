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

class MainDisplay: AppCompatActivity(){

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigatin_view)
        setupWithNavController(bottomNavigationView, navController)
        var bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigatin_view)
        bottomNav.selectedItemId = R.id.car_statusFragment

    }

    fun logout(view: View) {
        var intent = Intent(this, StartScreen::class.java)
        this.startActivity(intent)
    }

}