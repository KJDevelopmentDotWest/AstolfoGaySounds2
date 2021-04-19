package com.kjdevelopmentdotwest.astolfogaysounds2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.kjdevelopmentdotwest.astolfogaysounds2.R
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.UserData

class CasualPostureActivity : AppCompatActivity() {
    lateinit var blackSkirtButton: Button
    lateinit var greenSkirtButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_casual_posture)

        blackSkirtButton = findViewById(R.id.blackSkirtButton)
        greenSkirtButton = findViewById(R.id.greenSkirtButton)

        blackSkirtButton.setOnClickListener {
            onSkirtSelect(0)
        }
        greenSkirtButton.setOnClickListener {
            onSkirtSelect(1)
        }
    }

    override fun onStop() {
        super.onStop()
        val saveThread = SaveUserDataThread()
        saveThread.priority = Thread.MAX_PRIORITY
        saveThread.start()
    }

    private fun onSkirtSelect(place: Int){
        UserData.casualPostureSkirts[place].drawCasual()
        UserData.casualPostureSkirts.forEach {
            if (it.status == 2) {
                it.status = 1
                return@forEach
            }
        }
        UserData.casualPostureSkirts[place].status = 2
    }

    inner class SaveUserDataThread: Thread(){
        override fun run(){
            UserData.saveUserData()
        }
    }
}