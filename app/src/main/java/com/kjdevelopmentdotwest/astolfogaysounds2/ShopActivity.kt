package com.kjdevelopmentdotwest.astolfogaysounds2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ShopActivity : AppCompatActivity() {
    lateinit var casualPostureButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        casualPostureButton = findViewById(R.id.casualPostureButton)

        casualPostureButton.setOnClickListener {
            startActivity(Intent(this, CasualPostureActivity::class.java))
        }
    }
}