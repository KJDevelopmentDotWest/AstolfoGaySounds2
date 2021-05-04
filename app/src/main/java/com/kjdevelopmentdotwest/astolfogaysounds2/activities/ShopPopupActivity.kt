package com.kjdevelopmentdotwest.astolfogaysounds2.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.ImageFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.R
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.UserData

class ShopPopupActivity : AppCompatActivity() {

    companion object{
        var answerCode = 0
    }

    private lateinit var yesButton: Button
    private lateinit var noButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_popup)

        answerCode = 0

        yesButton = findViewById(R.id.shopPopupYesButton)
        noButton = findViewById(R.id.shopPopupNoButton)

        val price = intent.getIntExtra("price", 0)

        yesButton.isEnabled = price < UserData.moneyCount

        yesButton.setOnClickListener {
            UserData.moneyCount -= price
            setResult(1)
            finish()
        }

        noButton.setOnClickListener {
            setResult(0)
            finish()
        }

        val displayMetrics = ImageFactory.displayMetrics

        val width = displayMetrics!!.widthPixels
        val height = displayMetrics.heightPixels

        window.setLayout(width.times(0.6).toInt(), height.times(0.22).toInt())

        val params = window.attributes
        params.gravity = Gravity.CENTER
        params.x = 0
        params.y = 0

        window.attributes = params
    }
}