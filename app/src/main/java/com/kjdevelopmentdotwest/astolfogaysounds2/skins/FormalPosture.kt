package com.kjdevelopmentdotwest.astolfogaysounds2.skins

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.ImageFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.Item
import com.kjdevelopmentdotwest.astolfogaysounds2.R
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.schoolPosture.SchoolPosture
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.UserData

//status 0 - not sold, 1 - sold, 2 - equipped

open class FormalPosture {
    companion object{
        var status: Int = 0

        const val BLAZER_OFFSET_LEFT = 0.529f
        const val BLAZER_OFFSET_TOP = 0.568f
        const val PANTS_OFFSET_LEFT = 0.442f
        const val PANTS_OFFSET_TOP = 0.908f
        private val baseBitmap: Bitmap = BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.formal_astolfo)
        val previewBitmap: Bitmap = ImageFactory.generatePreview(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.formal_astolfo))
        private val blazerBitmap: Bitmap = BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.formal_blazer_red)
        private val pantsBitmap: Bitmap = BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.formal_pants_black)
        val bitmapsToDrawFormal: ArrayList<Item> = arrayListOf(
            Item(baseBitmap, 0f, 0f),
            Item(blazerBitmap, BLAZER_OFFSET_LEFT, BLAZER_OFFSET_TOP),
            Item(pantsBitmap, PANTS_OFFSET_LEFT, PANTS_OFFSET_TOP))

        fun drawFormal() {
            ImageFactory.mergeScaleBitmaps(bitmapsToDrawFormal)
        }
    }

    open fun  addToDrawQueue(){
        if (CasualPosture.status.compareTo(2) == 0){
            CasualPosture.status = 1
        }
        if (DefaultPosture.status.compareTo(2) == 0){
            DefaultPosture.status = 1
        }
        if (SchoolPosture.status.compareTo(2) == 0){
            SchoolPosture.status = 1
        }
        status = 2
    }
}