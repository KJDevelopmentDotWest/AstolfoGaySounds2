package com.kjdevelopmentdotwest.astolfogaysounds2

import android.content.res.Resources
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
        var resources: Resources? = null

        private fun getRes(){
            imageWidth = displayMetrics!!.widthPixels
            imageHeight = displayMetrics!!.heightPixels - (24) * displayMetrics!!.density.toInt() // 24dp status bar
        }

        fun scaleBitmap(src : Bitmap){
            if (imageWidth <= 0 || imageHeight <= 0){
                getRes()
            }

            val result = Bitmap.createBitmap(imageWidth, imageHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(result)

            canvas.drawBitmap(Bitmap.createScaledBitmap(src, imageWidth, imageHeight, true), 0f, 0f, null)

            resultImage = result
        }

        fun mergeScaleBitmaps(back : Bitmap, front : Bitmap, offsetLeft : Float, offsetTop : Float){
            if (imageWidth <= 0 || imageHeight <= 0){
                getRes()
            }

            val result = Bitmap.createBitmap(imageWidth, imageHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(result)

            canvas.drawBitmap(Bitmap.createScaledBitmap(back, imageWidth, imageHeight, true), 0f, 0f, null)
            canvas.drawBitmap(Bitmap.createScaledBitmap(front, 500, 500, true), offsetLeft, offsetTop, null)

            resultImage = result
        }
    }
}