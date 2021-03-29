package com.kjdevelopmentdotwest.astolfogaysounds2

import android.content.SharedPreferences
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
        MainActivity.casualPostureSkirts[place].draw()
        MainActivity.casualPostureSkirts.forEach{
            if (it.status == 2){
                it.status = 1
                return@forEach
            }
        }
        MainActivity.formalPostureBlazers.forEach{
            if (it.status == 2){
                it.status = 1
                return@forEach
            }
        }
        MainActivity.formalPosturePants.forEach {
            if (it.status == 2){
                it.status = 1
                return@forEach
            }
        }
        MainActivity.casualPostureSkirts[place].status = 2
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
            editor.putLong("clicks", MainActivity.clickCount)
            editor.putLong("money", MainActivity.moneyCount)
        }

        private fun saveBackgroundData(editor: SharedPreferences.Editor){
            editor.putInt("blackBackground", MainActivity.backgrounds[0].status)
            editor.putInt("greenBackground", MainActivity.backgrounds[1].status)
        }

        private fun saveCasualPostureData(editor: SharedPreferences.Editor){
            editor.putInt("blackSkirt", MainActivity.casualPostureSkirts[0].status)
            editor.putInt("greenSkirt", MainActivity.casualPostureSkirts[1].status)
        }
    }
}