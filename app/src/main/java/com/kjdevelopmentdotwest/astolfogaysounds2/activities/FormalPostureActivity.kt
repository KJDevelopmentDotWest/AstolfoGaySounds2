package com.kjdevelopmentdotwest.astolfogaysounds2.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.kjdevelopmentdotwest.astolfogaysounds2.R
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.FormalPosture
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.UserData

class FormalPostureActivity : AppCompatActivity() {
    private lateinit var redBlazerButton: Button
    lateinit var blackPantsButton: ImageButton
    lateinit var greenPantsButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formal_posture)

        redBlazerButton = findViewById(R.id.formalRedBlazerButton)
        blackPantsButton = findViewById(R.id.formalPantsBlackButton)
        greenPantsButton = findViewById(R.id.formalPantsGreenButton)

        redBlazerButton.setOnClickListener {
            onBlazerSelect(0)
        }

        blackPantsButton.setOnClickListener {
            onPantsSelect(0)
        }

        greenPantsButton.setOnClickListener {
            onPantsSelect(1)
        }
    }

    override fun onStop() {
        super.onStop()
        val saveThread = SaveUserDataThread()
        saveThread.priority = Thread.MAX_PRIORITY
        saveThread.start()
    }

    private fun onBlazerSelect(place: Int){

        if (UserData.formalPostureBlazers[place].status.compareTo(0) == 0){
            startActivity(Intent(this, ShopPopupActivity::class.java))
        }

        if (UserData.formalPostureBlazers[place].status.compareTo(1) == 0) {
            UserData.formalPostureBlazers[place].addToDrawQueue()
            UserData.formalPostureBlazers.forEach{
                if (it.status == 2){
                    it.status = 1
                    return@forEach
                }
            }
            UserData.casualPostureSkirts.forEach {
                if (it.status == 2){
                    it.status = 1
                    return@forEach
                }
            }
            UserData.formalPostureBlazers[place].status = 2
            FormalPosture.draw()
        }

    }

    private fun onPantsSelect(place: Int){
        //UserData.formalPosturePants[place].status = 0
        if (UserData.formalPosturePants[place].status.compareTo(0) == 0){
            startActivity(Intent(this, ShopPopupActivity::class.java))
            UserData.formalPosturePants[place].status = 0
        }

        if (UserData.formalPosturePants[place].status.compareTo(1) == 0){
            UserData.formalPosturePants[place].addToDrawQueue()
            UserData.formalPosturePants.forEach{
                if (it.status == 2){
                    it.status = 1
                    return@forEach
                }
            }
            UserData.casualPostureSkirts.forEach {
                if (it.status == 2){
                    it.status = 1
                    return@forEach
                }
            }
            UserData.formalPosturePants[place].status = 2
            FormalPosture.draw()
        }
    }

    inner class SaveUserDataThread: Thread(){
        override fun run(){
            UserData.saveUserData()
        }
    }
}