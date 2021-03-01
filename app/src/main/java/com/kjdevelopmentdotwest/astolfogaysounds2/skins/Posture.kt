package com.kjdevelopmentdotwest.astolfogaysounds2.skins

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.ImageFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.R

open class Posture {
    companion object{
        var backgroundBitmap: Bitmap = BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.background_black)
        var postureBitmap: Bitmap = BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.casual_astolfo)
        var skirtBitmap: Bitmap = BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.casual_skirt_black)
    }

    open fun draw(){
        ImageFactory.mergeScaleBitmaps(backgroundBitmap, postureBitmap, skirtBitmap)

    }
}

