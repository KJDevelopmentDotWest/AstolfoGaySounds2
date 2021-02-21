package com.kjdevelopmentdotwest.astolfogaysounds2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.CasualPosture
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.DefaultPosture
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.FormalPosture
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.SchoolPosture

class ShopActivity : AppCompatActivity() {
    private lateinit var casualPosturePreview: ImageView
    lateinit var formalPosturePreview: ImageView
    lateinit var schoolPosturePreview: ImageView
    lateinit var defaultPosturePreview: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        casualPosturePreview = findViewById(R.id.casualPosturePreview)
        formalPosturePreview = findViewById(R.id.formalPosturePreview)
        schoolPosturePreview = findViewById(R.id.schoolPosturePreview)
        defaultPosturePreview = findViewById(R.id.defaultPosturePreview)

        casualPosturePreview.setImageBitmap(CasualPosture.previewBitmap)
        formalPosturePreview.setImageBitmap(FormalPosture.previewBitmap)
        schoolPosturePreview.setImageBitmap(SchoolPosture.previewBitmap)
        defaultPosturePreview.setImageBitmap(DefaultPosture.previewBitmap)
        casualPosturePreview.setOnClickListener {
            startActivity(Intent(this, CasualPostureActivity::class.java))
        }
    }
}