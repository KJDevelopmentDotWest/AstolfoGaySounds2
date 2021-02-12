package com.kjdevelopmentdotwest.astolfogaysounds2.skins

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.ImageFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.R

open class CasualPosture {
    companion object{
        const val SKIRT_OFFSET_TOP = 10f
        const val SKIRT_OFFSET_LEFT = 10f
        var baseBitmap: Bitmap = BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.astolfo)
    }
}