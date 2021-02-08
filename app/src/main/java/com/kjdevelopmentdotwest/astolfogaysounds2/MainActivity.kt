package com.kjdevelopmentdotwest.astolfogaysounds2

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainImage = findViewById<ImageView>(R.id.imageView)
        val shopButton = findViewById<Button>(R.id.shopButton)

        if (ImageFactory.resultImage != null){
            mainImage.setImageBitmap(ImageFactory.resultImage)
        } else {
            mainImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.astolfo))
        }

        mainImage.setOnClickListener {
            View.OnClickListener {
                ImageFactory.mergeBitmaps(BitmapFactory.decodeResource(resources, R.drawable.astolfo), BitmapFactory.decodeResource(resources, R.drawable.astolfoface), 100f, 50f)
                mainImage.setImageBitmap(ImageFactory.resultImage)
            }
        }

        shopButton.setOnClickListener{
            ImageFactory.mergeBitmaps(BitmapFactory.decodeResource(resources, R.drawable.astolfo), BitmapFactory.decodeResource(resources, R.drawable.astolfoface), 100f, 50f)
            mainImage.setImageBitmap(ImageFactory.resultImage)
        }
    }
}