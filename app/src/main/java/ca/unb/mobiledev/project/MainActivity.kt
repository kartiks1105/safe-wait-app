package ca.unb.mobiledev.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.CacheRequest
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.get_student_id)
        var button = findViewById<Button>(R.id.verify)
        button.setOnClickListener {
            val loading = findViewById<ProgressBar>(R.id.progressBar)
            button.text = ""
            button.isEnabled = false
            loading.visibility = View.VISIBLE
//            val intent = Intent(this, MainDisplay::class.java)
//            this.startActivity
            //verifyStudentInfo().start()
        }
    }

//    private fun verifyStudentInfo() : Thread {
//        return Thread {
//            val url = URL("http://127.0.0.1:5000/getStudentInformation?studentId=1")
//            val connection = url.openConnection() as HttpURLConnection
//            if (connection.responseCode == 200) {
//                val inputStream = connection.inputStream
//                val inputStreamReader = InputStreamReader(inputStream, "UTF-8")
//                val request = Gson().fromJson(inputStreamReader, StudentInformation::class.java)
//                display(request)
//                inputStreamReader.close()
//                inputStream.close()
//            }
//        }
//    }
//
//    private fun display(request: StudentInformation) {
//        println(request)
//    }
}