package com.kjdevelopmentdotwest.astolfogaysounds2

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    lateinit var  mainImage: ImageView
    lateinit var shopButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainImage = findViewById<ImageView>(R.id.imageView)
        shopButton = findViewById<Button>(R.id.shopButton)

        setUp()
    }

    override fun onStart() {
        super.onStart()
        if (ImageFactory.resultImage != null){
            mainImage.setImageBitmap(ImageFactory.resultImage)
        } else {
            ImageFactory.scaleBitmap(BitmapFactory.decodeResource(resources, R.drawable.astolfo))
            mainImage.setImageBitmap(ImageFactory.resultImage)
        }
    }

    override fun onResume() {
        super.onResume()
        if (ImageFactory.resultImage != null){
            mainImage.setImageBitmap(ImageFactory.resultImage)
        } else {
            ImageFactory.scaleBitmap(BitmapFactory.decodeResource(resources, R.drawable.astolfo))
            mainImage.setImageBitmap(ImageFactory.resultImage)
        }
    }

    private fun setUp(){
        val displayMetrics = DisplayMetrics()
        val windowManager = applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        ImageFactory.displayMetrics = displayMetrics

        mainImage.setOnClickListener {
            View.OnClickListener {
                ImageFactory.mergeScaleBitmaps(BitmapFactory.decodeResource(resources, R.drawable.astolfo), BitmapFactory.decodeResource(resources, R.drawable.astolfoface), 100f, 50f)
                mainImage.setImageBitmap(ImageFactory.resultImage)
            }
        }

        shopButton.setOnClickListener{
            ImageFactory.mergeScaleBitmaps(BitmapFactory.decodeResource(resources, R.drawable.astolfo), BitmapFactory.decodeResource(resources, R.drawable.astolfoface), 100f, 50f)
            mainImage.setImageBitmap(ImageFactory.resultImage)
        }
    }
}