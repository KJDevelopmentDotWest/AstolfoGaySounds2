package com.kjdevelopmentdotwest.astolfogaysounds2

import android.graphics.Bitmap
import android.graphics.Canvas

class ImageFactory {
    companion object{

        var resultImage : Bitmap? = null

        fun drawing(key : Bitmap) : Bitmap {

            val bitmap = key.copy(Bitmap.Config.ARGB_8888, true)

            val canvas = Canvas(bitmap)

            canvas.drawARGB(255, 255, 255, 255)

            return bitmap
        }

        fun mergeBitmaps(back : Bitmap, front : Bitmap, offsetLeft: Float, offsetTop: Float){
            var result = Bitmap.createBitmap(1080, 1680, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(result)

            canvas.drawBitmap(Bitmap.createScaledBitmap(back, 1080, 1680, true), 0f, 0f, null)
            canvas.drawBitmap(Bitmap.createScaledBitmap(front, 300, 1000, true), offsetLeft, offsetTop, null)

            resultImage = result
        }
    }

}