package ca.unb.mobiledev.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import ca.unb.mobiledev.project.databinding.ActivityMainBinding
import com.google.gson.Gson
import org.json.JSONObject
import java.io.InputStreamReader
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
//            val intent = Intent(this, MainDisplay::class.java)
//            this.startActivity(intent)
            verifyStudentInfo().start()
        }
    }

    private fun verifyStudentInfo() : Thread {
        return Thread {
            val url = URL("http://10.0.2.2:5000/getStudentInformation?studentId=10")

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

                    } else {
                        println("IN")
                        runOnUiThread {
                            kotlin.run {
                                var studentId = findViewById<EditText>(R.id.student_id)
                                var password = findViewById<EditText>(R.id.password)
                                studentId.error = "Invalid Student Id or password"
                                password.error = "Invalid Student Id or password"
                            }
                        }
                    }

                } catch (e: Exception) {

                }



//                var studentInformation = JSONObject(stringBuilder.toString()).get("StudentInformation") as JSONObject?
//                if (studentInformation != null) {
//                    Log.i("json", studentInformation.get("address").toString())
//                } else {
//                    var studentId = findViewById<EditText>(R.id.student_id)
//                    var password = findViewById<EditText>(R.id.password)
//                    studentId.error = "Invalid Student Id or password"
//                    password.error = "Invalid Student Id or password"
//                }
            }
        }
    }
}