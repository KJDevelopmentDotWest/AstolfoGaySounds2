package com.kjdevelopmentdotwest.astolfogaysounds2.skins

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.ImageFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.R
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.Item

open class SchoolPosture {
    companion object{
        var status: Int = 0

        const val SKIRT_OFFSET_TOP = 100f
        const val SKIRT_OFFSET_LEFT = 100f
        var baseBitmap: Bitmap = BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.school_astolfo)
        var previewBitmap: Bitmap = ImageFactory.generatePreview(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.school_astolfo))
        var bitmapsToDraw: ArrayList<Item> = arrayListOf(
            Item(CasualPosture.baseBitmap, 0f, 0f)
        )
    }

    open fun addToDrawQueue(){
        if (FormalPosture.status.compareTo(2) == 0){
            FormalPosture.status = 1
        }
        if (CasualPosture.status.compareTo(2) == 0){
            CasualPosture.status = 1
        }
        if (DefaultPosture.status.compareTo(2) == 0){
            DefaultPosture.status = 1
        }
        status = 2
    }
}