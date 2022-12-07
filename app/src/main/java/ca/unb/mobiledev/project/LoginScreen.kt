package ca.unb.mobiledev.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import ca.unb.mobiledev.project.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.concurrent.schedule

class LoginScreen : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val profileType = intent.extras?.get("ProfileType")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.login_form)
        var button = findViewById<Button>(R.id.verify)
        button.setOnClickListener {
            val loading = findViewById<ProgressBar>(R.id.progressBar)
            button.text = ""
            button.isEnabled = false
            loading.visibility = View.VISIBLE
            when (profileType) {
                "Student" -> {
                    Timer().schedule(2000){
                        getStudentInfo()
                    }

                }
                "Driver" -> {
                    Timer().schedule(2000){
                        getDriverInformation()
                    }
                }
                else -> {
                    throw Exception("ProfileType Mismatched")
                }
            }
        }
    }



    private fun getStudentInfo() {
        var studentId = findViewById<EditText>(R.id.student_id)
        var password = findViewById<EditText>(R.id.password)
        val credential = Credential(studentId.text.toString(), password.text.toString())
        val call = Client().getAPI().getStudentInformation(credential)
        call!!.enqueue(object : Callback<Student?> {
            override fun onResponse(call: Call<Student?>, response: Response<Student?>) {
                val studentInformation = response.body()?.studentInformation
                if (studentInformation?.student_id != null) {
                    var intent = Intent(this@LoginScreen, StudentDisplay::class.java)
                    this@LoginScreen.startActivity(intent)
                } else {
                    studentId.error = ("incorrect student id or password")
                    password.error = ("incorrect student id or password")
                    var progressBar = findViewById<ProgressBar>(R.id.progressBar)
                    progressBar.visibility = View.INVISIBLE
                    var verify = findViewById<Button>(R.id.verify)
                    verify.text = "VERIFY"
                }
            }

            override fun onFailure(call: Call<Student?>?, t: Throwable) {
                // setting text to our text view when
                // we get error response from API.
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getDriverInformation() {
        var studentId = findViewById<EditText>(R.id.student_id)
        var password = findViewById<EditText>(R.id.password)
        val credential = Credential(studentId.text.toString(), password.text.toString())
        val call = Client().getAPI().getDriverInformation(credential)
        call!!.enqueue(object : Callback<Driver?> {
            override fun onResponse(call: Call<Driver?>, response: Response<Driver?>) {
                val driverInformation = response.body()?.driverInformation
                if (driverInformation?.student_id != null) {
                    var intent = Intent(this@LoginScreen, DriverDisplay::class.java)
                    this@LoginScreen.startActivity(intent)
                } else {
                    studentId.error = ("incorrect student id or password")
                    password.error = ("incorrect student id or password")
                    var progressBar = findViewById<ProgressBar>(R.id.progressBar)
                    progressBar.visibility = View.INVISIBLE
                    var verify = findViewById<Button>(R.id.verify)
                    verify.text = "VERIFY"
                }
            }

            override fun onFailure(call: Call<Driver?>?, t: Throwable) {
                // setting text to our text view when
                // we get error response from API.
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}