package ca.unb.mobiledev.project

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import ca.unb.mobiledev.project.databinding.ActivityMainBinding
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.get_student_id)
        var button = findViewById<Button>(R.id.verify)
        button.setOnClickListener {
            val loading = findViewById<ProgressBar>(R.id.progressBar)
            button.text = ""
            button.isEnabled = false
            loading.visibility = View.VISIBLE
            verifyStudentInfo(this, loading, button).start()
        }
    }

    private fun verifyStudentInfo(parentActivity: AppCompatActivity,
                                  loading: ProgressBar, verify: Button) : Thread {
        return Thread {

            var studentId = findViewById<EditText>(R.id.student_id)
            var password = findViewById<EditText>(R.id.password)

            val url = URL("http://10.0.2.2:5000/getStudentInformation?studentId="
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
                    var studentInformation = response.get("StudentInformation")
                    if (studentInformation.javaClass.kotlin.qualifiedName != null) {
                        var intent = Intent(parentActivity, MainDisplay::class.java)
                        intent.putExtra("StudentInformation", studentInformation.toString())
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