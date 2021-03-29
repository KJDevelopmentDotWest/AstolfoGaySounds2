package com.kjdevelopmentdotwest.astolfogaysounds2.skins

import android.graphics.Bitmap
import com.kjdevelopmentdotwest.astolfogaysounds2.Item

//status 0 - not sold, 1 - sold, 2 - equipped

class FormalPosturePants(var resBitmap: Bitmap, var status: Int): FormalPosture() {

    override fun draw() {
        resultMap[2] = Item(resBitmap, PANTS_OFFSET_LEFT, PANTS_OFFSET_TOP)
        super.draw()
    }

}