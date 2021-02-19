package com.kjdevelopmentdotwest.astolfogaysounds2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CasualPostureActivity : AppCompatActivity() {
    lateinit var blackSkirtButton: Button
    lateinit var greenSkirtButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_casual_posture)

        blackSkirtButton = findViewById(R.id.blackSkirtButton)
        greenSkirtButton = findViewById(R.id.greenSkirtButton)

        blackSkirtButton.setOnClickListener {
            MainActivity.casualPostureSkirts[0].draw()
            MainActivity.casualPostureSkirts.forEach{
                if (it.status == 2){
                    it.status = 1
                    return@forEach
                }
            }
            MainActivity.casualPostureSkirts[0].status = 2
        }
        greenSkirtButton.setOnClickListener {
            MainActivity.casualPostureSkirts[1].draw()
            MainActivity.casualPostureSkirts.forEach{
                if (it.status == 2){
                    it.status = 1
                    return@forEach
                }
            }
            MainActivity.casualPostureSkirts[1].status = 2
        }
    }
}