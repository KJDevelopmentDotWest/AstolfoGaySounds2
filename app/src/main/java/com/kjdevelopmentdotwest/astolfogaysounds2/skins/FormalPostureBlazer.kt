package com.kjdevelopmentdotwest.astolfogaysounds2.skins

import android.graphics.Bitmap
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.Item

//status 0 - not sold, 1 - sold, 2 - equipped

class FormalPostureBlazer(override var resBitmap: Bitmap, override var status: Int): FormalPosture(), ClothItem {

    override fun addToDrawQueue(){
        super.addToDrawQueue()
        bitmapsToDraw[1] = Item(resBitmap, BLAZER_OFFSET_LEFT, BLAZER_OFFSET_TOP)
    }
}