package com.kjdevelopmentdotwest.astolfogaysounds2

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.FormalPostureBlazer

class FormalPostureActivity : AppCompatActivity() {
    private lateinit var redBlazerButton: Button
    lateinit var blackPantsButton: Button
    lateinit var greenPantsButton: Button

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
        MainActivity.formalPostureBlazers[place].draw()
        MainActivity.formalPostureBlazers.forEach{
            if (it.status == 2){
                it.status = 1
                return@forEach
            }
        }
        MainActivity.casualPostureSkirts.forEach {
            if (it.status == 2){
                it.status = 1
                return@forEach
            }
        }
        MainActivity.formalPostureBlazers[place].status = 2
    }

    private fun onPantsSelect(place: Int){
        MainActivity.formalPosturePants[place].draw()
        MainActivity.formalPosturePants.forEach{
            if (it.status == 2){
                it.status = 1
                return@forEach
            }
        }
        MainActivity.casualPostureSkirts.forEach {
            if (it.status == 2){
                it.status = 1
                return@forEach
            }
        }
        MainActivity.formalPosturePants[place].status = 2
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