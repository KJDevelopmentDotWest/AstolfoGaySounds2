package com.kjdevelopmentdotwest.astolfogaysounds2.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.kjdevelopmentdotwest.astolfogaysounds2.R
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.*
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.schoolPosture.SchoolPosture

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
            if (CasualPosture.status.compareTo(0) == 0){
                val intent = Intent(this, ShopPopupActivity::class.java).apply {
                    putExtra("price", 1000)
                }
                startActivityForResult(intent, 1)
            } else {
                startActivity(Intent(this, CasualPostureActivity::class.java))
            }
        }
        formalPosturePreview.setOnClickListener {
            if (FormalPosture.status.compareTo(0) == 0){
                val intent = Intent(this, ShopPopupActivity::class.java).apply {
                    putExtra("price", 1000)
                }
                startActivityForResult(intent, 2)
            } else {
                startActivity(Intent(this, FormalPostureActivity::class.java))
            }
        }
        schoolPosturePreview.setOnClickListener {
            if (SchoolPosture.status.compareTo(0) == 0){
                val intent = Intent(this, ShopPopupActivity::class.java).apply {
                    putExtra("price", 1000)
                }
                startActivityForResult(intent, 3)
            } else {
                startActivity(Intent(this, SchoolPostureActivity::class.java))
            }
        }
        defaultPosturePreview.setOnClickListener {
            DefaultPosture.drawDefault()
        }
        backgroundPreview.setOnClickListener {
            startActivity(Intent(this, BackgroundActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 1){
            when (requestCode){
                1 -> CasualPosture.status = 1
                2 -> FormalPosture.status = 1
                3 -> SchoolPosture.status = 1
                4 -> DefaultPosture.status = 1
            }
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