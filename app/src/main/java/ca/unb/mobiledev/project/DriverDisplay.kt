package ca.unb.mobiledev.project

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DriverDisplay: AppCompatActivity(){

    lateinit var navControllerDriver: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.driver_homepage)
        val navHostFragmentDriver =
            supportFragmentManager.findFragmentById(R.id.nav_fragment_driver) as NavHostFragment
        navControllerDriver = navHostFragmentDriver.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav_driver)
        setupWithNavController(bottomNavigationView, navControllerDriver)

    }

    fun logout(view: View) {
        var intent = Intent(this, StartScreen::class.java)
        this.startActivity(intent)
    }

    fun startRide(view: View) {
        var address1 = "moco"
        var place = Place(address1)
        val call = Client().getAPI().getPredictions(place)
        call!!.enqueue(object : Callback<Predictions?> {
            override fun onResponse(call: Call<Predictions?>, response: Response<Predictions?>) {
                val predictions = response.body()?.predictions
                if (predictions != null) {
                    if (predictions.isNotEmpty()) {
                        address1 = predictions[0]
                        Toast.makeText(applicationContext, address1, Toast.LENGTH_SHORT).show()
                    } else {
                        //error
                    }
                }
            }

            override fun onFailure(call: Call<Predictions?>?, t: Throwable) {
                // setting text to our text view when
                // we get error response from API.
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}