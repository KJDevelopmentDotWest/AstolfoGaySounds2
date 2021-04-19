package com.kjdevelopmentdotwest.astolfogaysounds2.skins

import android.graphics.Bitmap

//status 0 - not sold, 1 - sold, 2 - equipped

class CasualPostureSkirt(override var resBitmap: Bitmap, override var status: Int): CasualPosture(), ClothItemInterface {

    override fun drawCasual(){
        skirtBitmap = resBitmap
        super.drawCasual()
    }
}