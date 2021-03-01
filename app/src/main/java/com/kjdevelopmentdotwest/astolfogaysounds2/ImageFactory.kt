package com.kjdevelopmentdotwest.astolfogaysounds2

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.DisplayMetrics


class ImageFactory {
    companion object{

        var imageWidth : Int = 0
        var imageHeight : Int = 0
        var displayMetrics : DisplayMetrics? = null
        var resultImage : Bitmap? = null
        var resultBackground: Bitmap? = null
        var resources: Resources? = null

        private fun getRes(){
            imageWidth = displayMetrics!!.widthPixels
            imageHeight = displayMetrics!!.heightPixels - (24) * displayMetrics!!.density.toInt() // 24dp status bar
        }

        fun scaleBitmap(src: Bitmap){
            if (imageWidth <= 0 || imageHeight <= 0){
                getRes()
            }

            val result = Bitmap.createBitmap(imageWidth, imageHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(result)

            canvas.drawBitmap(Bitmap.createScaledBitmap(src, imageWidth, imageHeight, true), 0f, 0f, null)

            resultImage = Bitmap.createScaledBitmap(src, imageWidth, imageHeight, true)
        }

        fun mergeScaleBitmaps(background: Bitmap, posture: Bitmap, skirt: Bitmap){

            val result = Bitmap.createBitmap(imageWidth, imageHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(result)

            //canvas.drawBitmap(Bitmap.createScaledBitmap(background, imageWidth, imageHeight, true), 0f, 0f, null)
            canvas.drawBitmap(Bitmap.createScaledBitmap(posture, imageWidth, imageHeight, true), 0f, 0f, null)
            canvas.drawBitmap(Bitmap.createScaledBitmap(skirt, imageWidth, imageHeight, true), 0f, 0f, null)

            resultImage = result
        }

        fun generatePreview(src: Bitmap): Bitmap{
            if (imageWidth <= 0 || imageHeight <= 0){
                getRes()
            }

            val result = Bitmap.createBitmap((imageWidth/2.1).toInt(), (imageHeight/2.1).toInt(), Bitmap.Config.ARGB_8888)
            val canvas = Canvas(result)

            canvas.drawBitmap(Bitmap.createScaledBitmap(src, (imageWidth/2.1).toInt(), (imageHeight/2.1).toInt(), true), 0f, 0f, null)

            return result
        }
    }
}