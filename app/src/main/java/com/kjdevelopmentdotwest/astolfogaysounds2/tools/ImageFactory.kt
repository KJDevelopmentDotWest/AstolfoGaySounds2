package com.kjdevelopmentdotwest.astolfogaysounds2.tools

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.DisplayMetrics


class ImageFactory() {

    companion object{

        var deviceWidth: Int = 0
        var deviceHeight: Int = 0
        var displayMetrics: DisplayMetrics? = null
        var resultImage: Bitmap? = null
        var resultBackground: Bitmap? = null
        var resources: Resources? = null

        fun getRes(){
            deviceWidth = displayMetrics!!.widthPixels
            deviceHeight = displayMetrics!!.heightPixels - (24 + 26) * displayMetrics!!.density.toInt() // 24dp status bar 26dp clicks info
        }

        @Deprecated("")
        fun scaleBitmap(src: Bitmap) {
            if (deviceWidth <= 0 || deviceHeight <= 0) {
                getRes()
            }

            val result = Bitmap.createBitmap(deviceWidth, deviceHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(result)

            canvas.drawBitmap(Bitmap.createScaledBitmap(src, deviceWidth, deviceHeight, true), 0f, 0f, null)

            resultImage = Bitmap.createScaledBitmap(src, deviceWidth, deviceHeight, true)
        }

        fun mergeScaleBitmaps(resources: ArrayList<Item>){
            val preResult = Bitmap.createBitmap(resources[0].res.width, resources[0].res.height, Bitmap.Config.ARGB_8888)
            val tempCanvas = Canvas(preResult)

            resources.forEach{
                val finalOffsetLeft = resources[0].res.width.times(it.offsetLeft)
                val finalOffsetTop = resources[0].res.height.times(it.offsetTop)
                tempCanvas.drawBitmap(it.res, finalOffsetLeft, finalOffsetTop, null)
            }

            val ratio = deviceWidth.toFloat().div(resources[0].res.width).times(0.98)

            val result = Bitmap.createBitmap(resources[0].res.width.times(ratio).toInt(), resources[0].res.height.times(ratio).toInt(), Bitmap.Config.ARGB_8888)
            val canvas = Canvas(result)

            canvas.drawBitmap(Bitmap.createScaledBitmap(preResult, resources[0].res.width.times(ratio).toInt(), resources[0].res.height.times(ratio).toInt(), true), 0f, 0f, null)

            resultImage = result
        }

        fun generatePreview(src: Bitmap): Bitmap{
            if (deviceWidth <= 0 || deviceHeight <= 0){
                getRes()
            }

            val result = Bitmap.createBitmap((deviceWidth /2.1).toInt(), (deviceHeight /2.1).toInt(), Bitmap.Config.ARGB_8888)
            val canvas = Canvas(result)

            canvas.drawBitmap(Bitmap.createScaledBitmap(src, (deviceWidth /2.1).toInt(), (deviceHeight /2.1).toInt(), true), 0f, 0f, null)

            return result
        }
    }
}