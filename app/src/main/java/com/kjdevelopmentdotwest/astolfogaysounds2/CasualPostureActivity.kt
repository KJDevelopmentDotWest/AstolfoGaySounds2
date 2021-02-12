package com.kjdevelopmentdotwest.astolfogaysounds2

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.CasualPostureSkirt

class CasualPostureActivity : AppCompatActivity() {
    lateinit var blackSkirtButton: Button
    lateinit var greenSkirtButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_casual_posture)

        blackSkirtButton = findViewById(R.id.blackSkirtButton)
        greenSkirtButton = findViewById(R.id.greenSkirtButton)

        val skirts = arrayListOf<CasualPostureSkirt>()
        skirts.add(CasualPostureSkirt(1, BitmapFactory.decodeResource(resources, R.drawable.blackskirt), 1))
        skirts.add(CasualPostureSkirt(2, BitmapFactory.decodeResource(resources, R.drawable.greenskirt), 1))

        blackSkirtButton.setOnClickListener {
            skirts[0].draw()
        }
        greenSkirtButton.setOnClickListener {
            skirts[1].draw()
        }
    }
}