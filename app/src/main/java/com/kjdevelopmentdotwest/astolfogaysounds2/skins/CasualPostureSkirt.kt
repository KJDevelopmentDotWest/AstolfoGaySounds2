package com.kjdevelopmentdotwest.astolfogaysounds2.skins

import android.graphics.Bitmap
import com.kjdevelopmentdotwest.astolfogaysounds2.ImageFactory

class CasualPostureSkirt(var id: Int, var resBitmap: Bitmap, var status: Int): CasualPosture() {
    private val offsetTop = SKIRT_OFFSET_TOP
    private val offsetLeft = SKIRT_OFFSET_LEFT

    fun draw(){
        ImageFactory.mergeScaleBitmaps(baseBitmap, resBitmap, offsetLeft, offsetTop)
        baseBitmap = ImageFactory.resultImage!!
    }
}