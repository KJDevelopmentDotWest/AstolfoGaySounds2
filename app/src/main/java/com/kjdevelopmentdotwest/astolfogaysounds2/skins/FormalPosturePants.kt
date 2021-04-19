package com.kjdevelopmentdotwest.astolfogaysounds2.skins

import android.graphics.Bitmap
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.Item

//status 0 - not sold, 1 - sold, 2 - equipped

class FormalPosturePants(override var resBitmap: Bitmap, override var status: Int): FormalPosture(), ClothItemInterface {

    override fun addToDrawQueue(){
        super.addToDrawQueue()
        bitmapsToDrawFormal[2] = Item(resBitmap, PANTS_OFFSET_LEFT, PANTS_OFFSET_TOP)
    }
}