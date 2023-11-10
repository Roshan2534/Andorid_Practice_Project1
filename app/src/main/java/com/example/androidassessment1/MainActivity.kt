package com.example.androidassessment1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signup = findViewById<Button>(R.id.button)

        signup.setOnClickListener{
            val register = Intent(this, SignUp::class.java)
            startActivity(register)
        }

    }
}