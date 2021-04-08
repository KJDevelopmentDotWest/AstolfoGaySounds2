package com.kjdevelopmentdotwest.astolfogaysounds2.activities

import android.content.SharedPreferences
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
        UserData.casualPostureSkirts[place].draw()
        UserData.casualPostureSkirts.forEach{
            if (it.status == 2){
                it.status = 1
                return@forEach
            }
        }
        UserData.formalPostureBlazers.forEach{
            if (it.status == 2){
                it.status = 1
                return@forEach
            }
        }
        UserData.formalPosturePants.forEach {
            if (it.status == 2){
                it.status = 1
                return@forEach
            }
        }
        UserData.casualPostureSkirts[place].status = 2
    }

    inner class SaveUserDataThread: Thread(){
        override fun run(){
            val sharedPreferences = getSharedPreferences("data", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            saveClickMoneyData(editor)
            saveCasualPostureData(editor)
            saveBackgroundData(editor)
            editor.apply()
        }

        private fun saveClickMoneyData(editor: SharedPreferences.Editor){
            editor.putLong("clicks", UserData.clickCount)
            editor.putLong("money", UserData.moneyCount)
        }

        private fun saveBackgroundData(editor: SharedPreferences.Editor){
            editor.putInt("blackBackground", UserData.backgrounds[0].status)
            editor.putInt("greenBackground", UserData.backgrounds[1].status)
        }

        private fun saveCasualPostureData(editor: SharedPreferences.Editor){
            editor.putInt("blackSkirt", UserData.casualPostureSkirts[0].status)
            editor.putInt("greenSkirt", UserData.casualPostureSkirts[1].status)
        }
    }
}