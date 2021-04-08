package com.kjdevelopmentdotwest.astolfogaysounds2.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.kjdevelopmentdotwest.astolfogaysounds2.R
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.*

class ShopActivity : AppCompatActivity() {
    private lateinit var casualPosturePreview: ImageView
    lateinit var formalPosturePreview: ImageView
    lateinit var schoolPosturePreview: ImageView
    lateinit var defaultPosturePreview: ImageView
    lateinit var backgroundPreview: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        casualPosturePreview = findViewById(R.id.casualPosturePreview)
        formalPosturePreview = findViewById(R.id.formalPosturePreview)
        schoolPosturePreview = findViewById(R.id.schoolPosturePreview)
        defaultPosturePreview = findViewById(R.id.defaultPosturePreview)
        backgroundPreview = findViewById(R.id.backgroundPreview)

        val drawImagesThread = DrawImagesThread()
        drawImagesThread.priority = Thread.MAX_PRIORITY
        drawImagesThread.start()

        casualPosturePreview.setOnClickListener {
            startActivity(Intent(this, CasualPostureActivity::class.java))
        }
        backgroundPreview.setOnClickListener {
            startActivity(Intent(this, BackgroundActivity::class.java))
        }
        formalPosturePreview.setOnClickListener {
            startActivity(Intent(this, FormalPostureActivity::class.java))
        }
    }

    inner class DrawImagesThread: Thread(){
        override fun run() {
            runOnUiThread {
                casualPosturePreview.setImageBitmap(CasualPosture.previewBitmap)
                formalPosturePreview.setImageBitmap(FormalPosture.previewBitmap)
                schoolPosturePreview.setImageBitmap(SchoolPosture.previewBitmap)
                defaultPosturePreview.setImageBitmap(DefaultPosture.previewBitmap)
                backgroundPreview.setImageBitmap(Background.previewBitmap)
            }
        }
    }
}