package com.kjdevelopmentdotwest.astolfogaysounds2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class BackgroundActivity : AppCompatActivity() {
    lateinit var blackBackgroundButton: Button
    lateinit var greenBackgroundButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background)

        blackBackgroundButton = findViewById(R.id.background_black)
        greenBackgroundButton = findViewById(R.id.background_green)

        blackBackgroundButton.setOnClickListener {
            MainActivity.backgrounds[0].draw()
            MainActivity.backgrounds.forEach{
                if (it.status == 2){
                    it.status = 1
                    return@forEach
                }
            }
            MainActivity.backgrounds[0].status = 2
        }

        greenBackgroundButton.setOnClickListener {
            MainActivity.backgrounds[1].draw()
            MainActivity.backgrounds.forEach{
                if (it.status == 2){
                    it.status = 1
                    return@forEach
                }
            }
            MainActivity.backgrounds[1].status = 2
        }
    }
}