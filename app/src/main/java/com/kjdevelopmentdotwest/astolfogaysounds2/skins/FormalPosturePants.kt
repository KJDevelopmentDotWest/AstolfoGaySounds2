package com.kjdevelopmentdotwest.astolfogaysounds2.skins

import android.graphics.Bitmap
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.Item

//status 0 - not sold, 1 - sold, 2 - equipped

class FormalPosturePants(var resBitmap: Bitmap, var status: Int): FormalPosture() {

//    override fun draw() {
//        //bitmapsToDraw[2] = Item(resBitmap, PANTS_OFFSET_LEFT, PANTS_OFFSET_TOP)
//        super.draw()
//    }

    fun addToDrawQueue(){
        bitmapsToDraw[2] = Item(resBitmap, PANTS_OFFSET_LEFT, PANTS_OFFSET_TOP)
    }

}