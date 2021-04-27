package com.kjdevelopmentdotwest.astolfogaysounds2.tools

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.DisplayMetrics


class ImageFactory {
    companion object{

        var imageWidth: Int = 0
        var imageHeight: Int = 0
        var displayMetrics: DisplayMetrics? = null
        var resultImage: Bitmap? = null
        var resultBackground: Bitmap? = null
        var resources: Resources? = null

        private fun getRes(){
            imageWidth = displayMetrics!!.widthPixels
            imageHeight = displayMetrics!!.heightPixels - (24 + 26) * displayMetrics!!.density.toInt() // 24dp status bar 26dp clicks info
        }

        @Deprecated("")
        fun scaleBitmap(src: Bitmap) {
            if (imageWidth <= 0 || imageHeight <= 0) {
                getRes()
            }

            val result = Bitmap.createBitmap(imageWidth, imageHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(result)

            canvas.drawBitmap(Bitmap.createScaledBitmap(src, imageWidth, imageHeight, true), 0f, 0f, null)

            resultImage = Bitmap.createScaledBitmap(src, imageWidth, imageHeight, true)
        }

        fun mergeScaleBitmaps(resources: ArrayList<Item>){
            val preResult = Bitmap.createBitmap(resources[0].res.width, resources[0].res.height, Bitmap.Config.ARGB_8888)
            val tempCanvas = Canvas(preResult)

            resources.forEach{
                val finalOffsetLeft = resources[0].res.width.times(it.offsetLeft)
                val finalOffsetTop = resources[0].res.height.times(it.offsetTop)
                tempCanvas.drawBitmap(it.res, finalOffsetLeft, finalOffsetTop, null)
            }

            val ratio = imageHeight.toFloat().div(resources[0].res.height).times(0.95) //??

            val result = Bitmap.createBitmap(imageWidth, imageHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(result)

            canvas.drawBitmap(Bitmap.createScaledBitmap(preResult, resources[0].res.width.times(ratio).toInt(), resources[0].res.height.times(ratio).toInt(), true), 0f, 0f, null)

            resultImage = result
        }

        fun generatePreview(src: Bitmap): Bitmap{
            if (imageWidth <= 0 || imageHeight <= 0){
                getRes()
            }

            val result = Bitmap.createBitmap((imageWidth /2.1).toInt(), (imageHeight /2.1).toInt(), Bitmap.Config.ARGB_8888)
            val canvas = Canvas(result)

            canvas.drawBitmap(Bitmap.createScaledBitmap(src, (imageWidth /2.1).toInt(), (imageHeight /2.1).toInt(), true), 0f, 0f, null)

            return result
        }
    }
}