package com.kjdevelopmentdotwest.astolfogaysounds2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.widget.Button

class ShopPopupActivity : AppCompatActivity() {
    lateinit var yesButton: Button
    lateinit var noButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_popup)

        yesButton = findViewById(R.id.shopPopupYesButton)
        noButton = findViewById(R.id.shopPopupNoButton)

        yesButton.setOnClickListener {
            finish()
        }

        noButton.setOnClickListener {
            finish()
        }

        val displayMetrics = ImageFactory.displayMetrics

        val width = displayMetrics!!.widthPixels
        val height = displayMetrics.heightPixels

        window.setLayout(width.times(0.6).toInt(), height.times(0.6).toInt())

        val params = window.attributes
        params.gravity = Gravity.CENTER
        params.x = 0
        params.y = 0

        window.attributes = params
    }
}