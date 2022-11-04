package ca.unb.mobiledev.project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StartScreen: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_driver)
        val student = findViewById<Button>(R.id.student)
        val driver = findViewById<Button>(R.id.driver)
        var intent = Intent(this, LoginScreen::class.java)
        student.setOnClickListener {
            intent.putExtra("ProfileType", "Student")
            this.startActivity(intent)
        }
        driver.setOnClickListener {
            intent.putExtra("ProfileType", "Driver")
            this.startActivity(intent)
        }
    }
}