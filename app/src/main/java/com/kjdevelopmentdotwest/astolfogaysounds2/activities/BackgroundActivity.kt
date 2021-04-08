package com.kjdevelopmentdotwest.astolfogaysounds2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.kjdevelopmentdotwest.astolfogaysounds2.R
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.UserData

class BackgroundActivity : AppCompatActivity() {
    lateinit var blackBackgroundButton: Button
    lateinit var greenBackgroundButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background)

        blackBackgroundButton = findViewById(R.id.background_black)
        greenBackgroundButton = findViewById(R.id.background_green)

        blackBackgroundButton.setOnClickListener {
            UserData.backgrounds[0].draw()
            UserData.backgrounds.forEach{
                if (it.status == 2){
                    it.status = 1
                    return@forEach
                }
            }
            UserData.backgrounds[0].status = 2
        }

        greenBackgroundButton.setOnClickListener {
            UserData.backgrounds[1].draw()
            UserData.backgrounds.forEach{
                if (it.status == 2){
                    it.status = 1
                    return@forEach
                }
            }
            UserData.backgrounds[1].status = 2
        }
    }
}