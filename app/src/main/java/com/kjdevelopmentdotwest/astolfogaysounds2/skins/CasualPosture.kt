package com.kjdevelopmentdotwest.astolfogaysounds2.skins

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.ImageFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.R

open class CasualPosture: Posture() {

    companion object{
        const val SKIRT_OFFSET_TOP = 1124f
        const val SKIRT_OFFSET_LEFT = 401f
        var baseBitmap: Bitmap = BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.casual_astolfo)
        var previewBitmap: Bitmap = ImageFactory.generatePreview(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.casual_astolfo))
    }

    override fun draw(){
        postureBitmap = baseBitmap
        super.draw()
    }
}