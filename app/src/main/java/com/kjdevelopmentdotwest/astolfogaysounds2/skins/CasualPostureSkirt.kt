package com.kjdevelopmentdotwest.astolfogaysounds2.skins

import android.graphics.Bitmap
import com.kjdevelopmentdotwest.astolfogaysounds2.ImageFactory

//status 0 - not sold, 1 - sold, 2 - equipped

class CasualPostureSkirt(var resBitmap: Bitmap, var status: Int): CasualPosture() {
    private val offsetTop = SKIRT_OFFSET_TOP
    private val offsetLeft = SKIRT_OFFSET_LEFT

    fun draw(){
        ImageFactory.mergeScaleBitmaps(baseBitmap, resBitmap, offsetLeft, offsetTop)
        baseBitmap = ImageFactory.resultImage!!
    }
}