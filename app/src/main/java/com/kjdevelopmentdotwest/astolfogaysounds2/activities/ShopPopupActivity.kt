package com.kjdevelopmentdotwest.astolfogaysounds2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.ImageFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.R

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