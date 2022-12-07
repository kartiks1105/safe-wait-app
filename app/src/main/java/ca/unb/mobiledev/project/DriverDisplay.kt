package ca.unb.mobiledev.project

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await

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

    private fun getBestRoute(route: List<String>, routesEditTextView: List<EditText>) {
        val addresses = Addresses(route)
        val call = Client().getAPI().getBestRoute(addresses)
        call!!.enqueue(object : Callback<RouteInfo?> {
            override fun onResponse(call: Call<RouteInfo?>, response: Response<RouteInfo?>) {
                val invalidPosition = response.body()?.invalidPosition
                if (invalidPosition != -1) {
                    routesEditTextView[invalidPosition!!].error = "invalid address"
                } else {
                    val routeSequence = response.body()!!.route
                    val duration = response.body()!!.duration
                    println(response.body())
                    //open new gui and show route
                    //update duration
                }
            }

            override fun onFailure(call: Call<RouteInfo?>?, t: Throwable) {
                // setting text to our text view when
                // we get error response from API.
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun startRide(view: View) {
        var routesEditTextView = ArrayList<EditText>()
        routesEditTextView.add(findViewById<EditText>(R.id.route1))
        routesEditTextView.add(findViewById<EditText>(R.id.route2))
        routesEditTextView.add(findViewById<EditText>(R.id.route3))
        routesEditTextView.add(findViewById<EditText>(R.id.route4))
        routesEditTextView.add(findViewById<EditText>(R.id.route5))

        var stops = ArrayList<String>()
        for (routeEditTextView in routesEditTextView) {
            if (routeEditTextView.text.isNotEmpty()) {
                stops.add(routeEditTextView.text.toString())
            }
        }

        getBestRoute(stops, routesEditTextView)

    }
}