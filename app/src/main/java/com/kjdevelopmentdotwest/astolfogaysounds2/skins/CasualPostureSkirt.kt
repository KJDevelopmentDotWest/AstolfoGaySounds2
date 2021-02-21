package com.kjdevelopmentdotwest.astolfogaysounds2.skins

import android.graphics.Bitmap
import com.kjdevelopmentdotwest.astolfogaysounds2.ImageFactory

//status 0 - not sold, 1 - sold, 2 - equipped

class CasualPostureSkirt(var resBitmap: Bitmap, var status: Int): CasualPosture() {

    override fun draw(){
        skirtBitmap = resBitmap
        super.draw()
    }
}