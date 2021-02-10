package com.kjdevelopmentdotwest.astolfogaysounds2

import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.DisplayMetrics
import android.view.Display


class ImageFactory {
    companion object{

        var imageWidth : Int = 0
        var imageHeight : Int = 0
        var displayMetrics : DisplayMetrics? = null
        var resultImage : Bitmap? = null

        private fun getRes(){
            imageWidth = displayMetrics!!.widthPixels
            imageHeight = displayMetrics!!.heightPixels - (24 + 50 + 50) * displayMetrics!!.density.toInt() // 24dp status bar 50dp bottom buttons 50dp counter
        }

        fun scaleBitmap(src : Bitmap){
            getRes()

            val result = Bitmap.createBitmap(imageWidth, imageHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(result)

            canvas.drawBitmap(Bitmap.createScaledBitmap(src, imageWidth, imageHeight, true), 0f, 0f, null)

            resultImage = result
        }

        fun mergeScaleBitmaps(back : Bitmap, front : Bitmap, offsetLeft : Float, offsetTop : Float){
            getRes()

            val result = Bitmap.createBitmap(imageWidth, imageHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(result)

            canvas.drawBitmap(Bitmap.createScaledBitmap(back, imageWidth, imageHeight, true), 0f, 0f, null)
            canvas.drawBitmap(Bitmap.createScaledBitmap(front, 300, 1000, true), offsetLeft, offsetTop, null)

            resultImage = result
        }
    }

}