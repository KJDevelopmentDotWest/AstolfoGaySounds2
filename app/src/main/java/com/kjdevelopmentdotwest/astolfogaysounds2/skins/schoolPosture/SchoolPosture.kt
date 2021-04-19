package com.kjdevelopmentdotwest.astolfogaysounds2.skins.schoolPosture

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.ImageFactory
import com.kjdevelopmentdotwest.astolfogaysounds2.R
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.CasualPosture
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.DefaultPosture
import com.kjdevelopmentdotwest.astolfogaysounds2.skins.FormalPosture
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.Item
import com.kjdevelopmentdotwest.astolfogaysounds2.tools.UserData

open class SchoolPosture {
    companion object{
        var status: Int = 0

        const val SHIRT_OFFSET_TOP = 0.262f
        const val SHIRT_OFFSET_LEFT = 0.076f
        const val SKIRT_OFFSET_TOP = 0.581f
        const val SKIRT_OFFSET_LEFT = 0.086f
        const val STOCKINGS_OFFSET_TOP = 0.792f
        const val STOCKINGS_OFFSET_LEFT = 0.229f
        private val baseBitmap: Bitmap = BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.school_astolfo)
        val previewBitmap: Bitmap = ImageFactory.generatePreview(baseBitmap)
        private val shirtBitmap: Bitmap = BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.school_shirt_white)
        private val skirtBitmap: Bitmap = BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.school_skirt_pink)
        private val stockingsBitmap: Bitmap = BitmapFactory.decodeResource(ImageFactory.resources, R.drawable.school_stockings_white)
        val bitmapsToDrawSchool: ArrayList<Item> = arrayListOf(
            Item(baseBitmap, 0f, 0f),
            Item(shirtBitmap, SHIRT_OFFSET_LEFT, SHIRT_OFFSET_TOP),
            Item(skirtBitmap, SKIRT_OFFSET_LEFT, SKIRT_OFFSET_TOP),
            Item(stockingsBitmap, STOCKINGS_OFFSET_LEFT, STOCKINGS_OFFSET_TOP)
        )

        fun drawSchool(){
            ImageFactory.mergeScaleBitmaps(bitmapsToDrawSchool)
        }
    }

    open fun addToDrawQueue(){
        if (FormalPosture.status == 2){
            FormalPosture.status = 1
        }
        if (CasualPosture.status == 2){
            CasualPosture.status = 1
        }
        if (DefaultPosture.status == 2){
            DefaultPosture.status = 1
        }
        status = 2
    }
}