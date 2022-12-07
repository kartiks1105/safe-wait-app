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
import com.fasterxml.jackson.databind.ObjectMapper
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.net.URL

class LoginScreen : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val profileType = intent.extras?.get("ProfileType")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.get_student_id)
        var button = findViewById<Button>(R.id.verify)
        button.setOnClickListener {
            val loading = findViewById<ProgressBar>(R.id.progressBar)
            button.text = ""
            button.isEnabled = false
            loading.visibility = View.VISIBLE
            if (profileType == "Student") {
                //verifyStudentInfo(this, loading, button).start()
                getStudentInfo()
            } else if (profileType == "Driver") {
                verifyDriverInfo(this, loading, button).start()
            } else {
                throw Exception("ProfileType Mismatched")
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
                    var intent = Intent(this@LoginScreen, MainDisplay::class.java)
                    this@LoginScreen.startActivity(intent)
                }
            }

            override fun onFailure(call: Call<Student?>?, t: Throwable) {
                // setting text to our text view when
                // we get error response from API.
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun verifyDriverInfo(parentActivity: AppCompatActivity,
                                  loading: ProgressBar, verify: Button) : Thread {
        return Thread {

            var studentId = findViewById<EditText>(R.id.student_id)
            var password = findViewById<EditText>(R.id.password)

            val url = URL("http://10.0.2.2:5000/getDriverInformation?studentId="
                    + studentId.text + "&password=" + password.text)

            with(url.openConnection() as HttpURLConnection) {
                requestMethod = "GET"  // optional default is GET

                println("\nSent 'GET' request to URL : $url; Response Code : $responseCode")

                val stringBuilder = StringBuilder()

                inputStream.bufferedReader().use {
                    it.lines().forEach { line ->
                        stringBuilder.append(line)
                    }
                }

                try {
                    var response = JSONObject(stringBuilder.toString())
                    var driverInformation = response.get("DriverInformation")
                    if (driverInformation.javaClass.kotlin.qualifiedName != null) {
                        var intent = Intent(parentActivity, MainDisplay::class.java)
                        intent.putExtra("DriverInformation", driverInformation.toString())
                        parentActivity.startActivity(intent)
                    } else {
                        runOnUiThread {
                            kotlin.run {
                                studentId.error = "Invalid Student Id or password"
                                password.error = "Invalid Student Id or password"
                                loading.visibility = View.INVISIBLE
                                verify.isEnabled = true
                                verify.text = "Verify"
                            }
                        }
                    }

                } catch (e: Exception) {

                }
            }
        }
    }
}