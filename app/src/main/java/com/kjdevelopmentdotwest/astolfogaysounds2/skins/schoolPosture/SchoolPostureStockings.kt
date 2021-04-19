package com.kjdevelopmentdotwest.astolfogaysounds2.skins.schoolPosture

import android.graphics.Bitmap
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.ClothItemInterface
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.Item

class SchoolPostureStockings(override var resBitmap: Bitmap, override var status: Int): SchoolPosture(), ClothItemInterface {

    override fun addToDrawQueue() {
        super.addToDrawQueue()
        bitmapsToDrawSchool[3] = Item(resBitmap, STOCKINGS_OFFSET_LEFT, STOCKINGS_OFFSET_TOP)
    }
}