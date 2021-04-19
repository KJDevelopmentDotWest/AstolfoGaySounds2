package com.kjdevelopmentdotwest.astolfogaysounds2.skins

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.ImageFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.R
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.schoolPosture.SchoolPosture
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.Item

//status 0 - not sold, 1 - sold, 2 - equipped

open class CasualPosture{

    companion object{
        var status: Int = 0

        const val SKIRT_OFFSET_TOP = 1124f
        const val SKIRT_OFFSET_LEFT = 401f
        var baseBitmap: Bitmap = BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.casual_astolfo)
        var previewBitmap: Bitmap = ImageFactory.generatePreview(BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.casual_astolfo))
        var skirtBitmap: Bitmap = BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.casual_skirt_black)
        var bitmapsToDrawCasual: ArrayList<Item> = arrayListOf(
            Item(baseBitmap, 0f, 0f),
            Item(skirtBitmap, 0.5f, 0.5f))
    }

    open fun drawCasual(){
        ImageFactory.mergeScaleBitmaps(bitmapsToDrawCasual)
    }

    open fun addToDrawQueue(){
        if (FormalPosture.status.compareTo(2) == 0){
            FormalPosture.status = 1
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