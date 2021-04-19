package com.kjdevelopmentdotwest.astolfogaysounds2.skins.schoolPosture

import android.graphics.Bitmap
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.ClothItemInterface
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.Item

class SchoolPostureSkirt(override var resBitmap: Bitmap, override var status: Int): SchoolPosture(), ClothItemInterface {

    override fun addToDrawQueue() {
        super.addToDrawQueue()
        bitmapsToDrawSchool[2] = Item(resBitmap, SKIRT_OFFSET_LEFT, SKIRT_OFFSET_TOP)
    }
}