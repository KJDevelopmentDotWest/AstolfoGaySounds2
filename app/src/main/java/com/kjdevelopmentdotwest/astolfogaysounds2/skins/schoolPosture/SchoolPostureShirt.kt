package com.kjdevelopmentdotwest.astolfogaysounds2.skins.schoolPosture

import android.graphics.Bitmap
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.ClothItemInterface
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.Item

class SchoolPostureShirt(override var resBitmap: Bitmap, override var status: Int): SchoolPosture(), ClothItemInterface {

    override fun addToDrawQueue() {
        super.addToDrawQueue()
        bitmapsToDrawSchool[1] = Item(resBitmap, SHIRT_OFFSET_LEFT, SHIRT_OFFSET_TOP)
    }
}