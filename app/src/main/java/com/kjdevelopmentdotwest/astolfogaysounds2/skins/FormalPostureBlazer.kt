package com.kjdevelopmentdotwest.astolfogaysounds2.skins

import android.graphics.Bitmap
import com.kjdevelopmentdotwest.astolfogaysounds2.Item

//status 0 - not sold, 1 - sold, 2 - equipped

class FormalPostureBlazer(var resBitmap: Bitmap, var status: Int): FormalPosture() {

    override fun draw() {
        resultMap[1] = Item(resBitmap, BLAZER_OFFSET_LEFT, BLAZER_OFFSET_TOP)
        super.draw()
    }
}