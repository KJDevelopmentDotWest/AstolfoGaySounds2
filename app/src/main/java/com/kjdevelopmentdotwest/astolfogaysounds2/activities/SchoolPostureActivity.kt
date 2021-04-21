package com.kjdevelopmentdotwest.astolfogaysounds2.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.kjdevelopmentdotwest.astolfogaysounds2.R
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.ClothItemInterface
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.schoolPosture.SchoolPosture
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.UserData

class SchoolPostureActivity : AppCompatActivity() {
    private lateinit var shirtWhiteButton: ImageButton
    private lateinit var shirtBlueButton: ImageButton
    private lateinit var skirtPinkButton: ImageButton
    private lateinit var skirtBlueButton: ImageButton
    private lateinit var stockingsWhiteButton: ImageButton
    private lateinit var stockingsBlueButton: ImageButton
    private var latestItem: ClothItemInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_posture)

        shirtWhiteButton = findViewById(R.id.schoolShirtWhite)
        shirtBlueButton = findViewById(R.id.schoolShirtBlue)
        skirtPinkButton = findViewById(R.id.schoolSkirtPink)
        skirtBlueButton = findViewById(R.id.schoolSkirtBlue)
        stockingsWhiteButton = findViewById(R.id.schoolStockingsWhite)
        stockingsBlueButton = findViewById(R.id.schoolStockingsBlue)

        shirtWhiteButton.setOnClickListener {
            onShirtSelect(0)
        }

        shirtBlueButton.setOnClickListener {
            onShirtSelect(1)
        }

        skirtPinkButton.setOnClickListener {
            onSkirtSelect(0)
        }

        skirtBlueButton.setOnClickListener {
            onSkirtSelect(1)
        }

        stockingsWhiteButton.setOnClickListener {
            onStockingsSelect(0)
        }

        stockingsBlueButton.setOnClickListener {
            onStockingsSelect(1)
        }
    }

    private fun onShirtSelect(place: Int){
        if (UserData.schoolPostureShirts[place].status == 0){
            latestItem = UserData.schoolPostureShirts[place]
            val intent = Intent(this, ShopPopupActivity::class.java).apply {
                putExtra("price", 1000)
            }
            startActivityForResult(intent, 1)
        }
        if (UserData.schoolPostureShirts[place].status == 1){
            UserData.schoolPostureShirts[place].addToDrawQueue()
            UserData.schoolPostureShirts.forEach {
                if (it.status == 2){
                    it.status = 1
                    return@forEach
                }
            }
            UserData.schoolPostureShirts[place].status = 2
        }
    }

    private fun onSkirtSelect(place: Int){
        if (UserData.schoolPostureSkirts[place].status == 0){
            latestItem = UserData.schoolPostureSkirts[place]
            val intent = Intent(this, ShopPopupActivity::class.java).apply {
                putExtra("price", 1000)
            }
            startActivityForResult(intent, 1)
        }
        if (UserData.schoolPostureSkirts[place].status == 1){
            UserData.schoolPostureSkirts[place].addToDrawQueue()
            UserData.schoolPostureSkirts.forEach {
                if (it.status == 2){
                    it.status = 1
                    return@forEach
                }
            }
            UserData.schoolPostureSkirts[place].status = 2
        }
    }

    private fun onStockingsSelect(place: Int){
        if (UserData.schoolPostureStockings[place].status == 0){
            latestItem = UserData.schoolPostureStockings[place]
            val intent = Intent(this, ShopPopupActivity::class.java).apply {
                putExtra("price", 1000)
            }
            startActivityForResult(intent, 1)
        }
        if (UserData.schoolPostureStockings[place].status == 1){
            UserData.schoolPostureStockings[place].addToDrawQueue()
            UserData.schoolPostureStockings.forEach {
                if (it.status == 2){
                    it.status = 1
                    return@forEach
                }
            }
            UserData.schoolPostureStockings[place].status = 2
        }
    }

    override fun onPause() {
        super.onPause()
        val saveThread = SaveUserDataThread()
        saveThread.priority = Thread.MAX_PRIORITY
        saveThread.start()
        SchoolPosture.draw()
    }

    inner class SaveUserDataThread: Thread(){
        override fun run(){
            UserData.saveUserData()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == 1) {
            latestItem?.status = 1
        }
    }
}